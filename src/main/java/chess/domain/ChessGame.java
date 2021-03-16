package chess.domain;

public class ChessGame {
    private Map<String, Piece>
    private boolean isRunning;

    public ChessGame() {
        isRunning = true;
    }

    public void endGame() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
