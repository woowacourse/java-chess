package chess;

public enum StatusCode {

    BAD_REQUEST(400),
    NOT_FOUND(404),
    ;

    private final int value;

    StatusCode(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
