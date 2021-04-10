package chess.controller.web.dto;

public class ExceptionMessageResponseDto {

    private final String exceptionMessage;

    public ExceptionMessageResponseDto(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
