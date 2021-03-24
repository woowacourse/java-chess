package chess.domain.chessgame;

public final class GameState {
    private boolean isRunning;

    public GameState() {
        this.isRunning = true;
    }

    public boolean isGameOver() {
        return !isRunning;
    }

    public void endGame() {
        this.isRunning = false;
    }
}
