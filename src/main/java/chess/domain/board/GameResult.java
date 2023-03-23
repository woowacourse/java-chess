package chess.domain.board;

public enum GameResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String displayName;

    GameResult(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
