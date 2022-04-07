package chess.web;

import static spark.Spark.exception;

import chess.dto.ExceptionResponseDto;

public class ExceptionHandler {

    private static final String INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE = "숫자를 입력하여야 합니다.";

    public void init() {
        setGlobalExceptionHandler();
        setNumberFormatExceptionHandler();
    }

    private void setGlobalExceptionHandler() {
        exception(Exception.class, (e, req, res) -> res.body(e.getMessage()));
    }

    private void setNumberFormatExceptionHandler() {
        exception(NumberFormatException.class, (e, req, res) -> {
            String exceptionResponse = new ExceptionResponseDto(INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE).toJson();
            res.body(exceptionResponse);
        });
    }
}
