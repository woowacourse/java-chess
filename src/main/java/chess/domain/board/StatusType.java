package chess.domain.board;

public enum StatusType {

    READY("게임을 시작해주세요."),
    PROCESSING("%d턴 - %s의 차례"),
    FINISHED("게임이 끝났습니다.");

    private String message;

    StatusType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
