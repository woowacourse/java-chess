package chess.domain.dto;

public class MessageDto {

    private String isOk;
    private String message;

    public MessageDto(String message) {
        this.isOk = "false";
        this.message = message;
    }
}
