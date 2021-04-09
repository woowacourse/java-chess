package chess.domain.chessgame;

public final class GameState {
    private boolean isRunning;

    public GameState(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public GameState() {
        this(true);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void endGame() {
        this.isRunning = false;
    }

    public void refresh() {
        isRunning = true;
    }
}
