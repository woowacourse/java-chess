package chess.dto;

public class GameDto {
    private final String gameId;
    private final String currentTurn;

    private GameDto(String gameId, String currentTurn) {
        this.gameId = gameId;
        this.currentTurn = currentTurn;
    }

    public static GameDto from(String gameId) {
        return new GameDto(gameId, null); // TODO: null 위험 존재
    }

    public String getGameId() {
        return gameId;
    }

    public String getCurrentTurn() {
        return currentTurn;
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "gameId='" + gameId + '\'' +
                ", currentTurn='" + currentTurn + '\'' +
                '}';
    }
}
