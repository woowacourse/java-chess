package domain.chessboard;

public enum GameResult {

    WIN("승"),
    DRAW("무승부"),
    LOSE("패");

    private final String value;

    GameResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
