package chess.controller.web.dto;

public class MessageResponseDto {
    private final String errorMsg;

    public MessageResponseDto(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
