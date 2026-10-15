package ru.trickluan.spring.lw5.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class Response {
    ///Уникальный идентификатор сообщения
    private String uid;
    ///Уникальный идентификатор операции
    private String operationUid;
    ///Время создания сообщения
    private String systemTime;
    ///Http код ответа
    private Codes code;
    ///Код ошибки
    private ErrorCodes errorCode;
    ///Сообщение об ошибке
    private ErrorMessages errorMessage;
    private double bonus;
}
