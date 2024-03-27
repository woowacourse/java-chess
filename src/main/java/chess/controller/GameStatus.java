package chess.controller;

public class GameStatus {
    private boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void stop() {
        isRunning = false;
    }
}
