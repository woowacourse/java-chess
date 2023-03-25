package chess.dto.outputView;

public final class PrintEndMessageDto {

    private final String message;

    public PrintEndMessageDto() {
        this.message = "체스 게임을 종료합니다.";
    }

    public String getMessage() {
        return message;
    }
}
