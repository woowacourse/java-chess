package domain.dto;

public final class StatusDto {

    private final GameStatus gameStatus;

    public StatusDto(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
