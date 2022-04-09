package chess.dto;

public class GameStateDto {
    private final String gameState;
    private final boolean isRunning;

    public GameStateDto(String gameState, boolean isFinished) {
        this.gameState = gameState;
        this.isRunning = isFinished;
    }
}
