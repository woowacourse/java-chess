package chess.service;

public enum Status {
    GAME_FINISHED(0),
    MOVE_SUCCESS(201),
    MOVE_FAILED(400);

    private final int statusCode;

    Status(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getCode() {
        return this.statusCode;
    }
}
