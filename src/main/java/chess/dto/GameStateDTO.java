package chess.dto;

public class GameStateDTO {
    private final String gameState;
    private final boolean isRunning;

    public GameStateDTO(String gameState, boolean isFinished) {
        this.gameState = gameState;
        this.isRunning = isFinished;
    }
}
