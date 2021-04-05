package chess.controller;

public enum HTTPStatusCode {
    SUCCESS(200),
    BAD_REQUEST(400),
    INTERNAL_SERVER_ERROR(500);

    private final int statusCode;

    HTTPStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }

    public int statusCode() {
        return statusCode;
    }
}
