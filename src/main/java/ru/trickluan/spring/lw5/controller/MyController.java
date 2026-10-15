package ru.trickluan.spring.lw5.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.trickluan.spring.lw5.exception.UnsupportedCodeException;
import ru.trickluan.spring.lw5.exception.ValidationFailedException;
import ru.trickluan.spring.lw5.model.*;
import ru.trickluan.spring.lw5.service.*;
import ru.trickluan.spring.lw5.util.DateTimeUtil;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final List<ModifyRequestService> modifyRequestServices;
    private final AnnualBonusService annualBonusService;
    private final QuarterBonusService quarterBonusService;

    public MyController(@Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        ValidationService validationService, List<ModifyRequestService> modifyRequestServices,
                        AnnualBonusService annualBonusService, QuarterBonusService quarterBonusService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestServices = modifyRequestServices;
        this.annualBonusService = annualBonusService;
        this.quarterBonusService = quarterBonusService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult result) {
        log.info("request: {}", request);

        Response response = createResponse(request);

        try {
            validationService.isValid(request, result);
        } catch (ValidationFailedException e) {
            log.error("Validation failed: {}", e.getMessage());
            return errorResponse(response, ErrorCodes.VALIDATION_EXCEPTION, ErrorMessages.VALIDATION,
                    HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e){
            log.error("Unsupported code: {}", e.getMessage());
            return errorResponse(response, ErrorCodes.UNSUPPORTED_EXCEPTION, ErrorMessages.UNSUPPORTED,
                    HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("UnknownException: {}", e.getMessage());
            return errorResponse(response, ErrorCodes.UNKNOWN_EXCEPTION, ErrorMessages.UNKNOWN,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        double bonusResult = annualBonusService.calculate(
                request.getPositions(),
                request.getSalary(),
                request.getBonus(),
                request.getWorkDays(),
                request.getLeapYear()
        );

        bonusResult += quarterBonusService.calculate(
                request.getPositions(),
                request.getSalary(),
                request.getBonus(),
                request.getWorkDays()
        );

        response.setBonus(bonusResult);


        modifyResponseService.modify(response);
        modifyRequestServices.forEach(s -> s.modify(request));

        return ResponseEntity.ok(response);
    }

    private Response createResponse(Request request) {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
    }

    private ResponseEntity<Response> errorResponse(
            Response response, ErrorCodes errorCode, ErrorMessages errorMessage, HttpStatus status
    ) {
        response.setCode(Codes.FAILED);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        return ResponseEntity.status(status).body(response);
    }
}
