package chess.service;

public class GameResult {
    private String winnerColor;
    private boolean isDraw;

    public GameResult(String winnerColor, boolean isDraw) {
        this.winnerColor = winnerColor;
        this.isDraw = isDraw;
    }

    public String getWinnerColor() {
        return winnerColor;
    }

    public boolean getIsDraw() {
        return isDraw;
    }
}
