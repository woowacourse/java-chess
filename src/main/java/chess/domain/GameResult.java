package chess.domain;

public enum GameResult {
    BLACK_WIN("흑색 진영의 승리"),
    WHITE_WIN("백색 진영의 승리"),
    DRAW("무승부");

    private final String message;

    GameResult(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
