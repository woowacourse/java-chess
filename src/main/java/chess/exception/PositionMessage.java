package chess.exception;

public enum PositionMessage {

    INVALID_ROW("잘못된 Row입니다. (정상 범위 1 ~ 9)"),
    INVALID_COLUMN("잘못된 Column입니다. (정상범위 a ~ h)");

    private final String message;

    PositionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
