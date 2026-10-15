package ru.trickluan.spring.lw5.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    /// Уникальный идентификатор сообщения
    @NotBlank
    @Size(max = 32)
    private String uid;

    ///Уникальный идентификатор операции
    @NotBlank
    @Size(max = 32)
    private String operationUid;

    ///Время создания сообщения
    @NotBlank
    private String systemTime;

    ///Уникальный идентификатор коммуникации
    @Min(1)
    @Max(100_000)
    private int communicationId;

    ///Наименование ресурса
    private String source;

    ///Уникальный идентификатор шаблона
    private Integer templateId;

    ///Код продукта
    private Integer productCode;

    ///Смс код
    private Integer smsCode;

    private Positions positions;
    private Double salary;
    private Double bonus;
    private Integer workDays;
    private Boolean leapYear = false;

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", communicationId=" + communicationId +
                ", source='" + source + '\'' +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }
}
