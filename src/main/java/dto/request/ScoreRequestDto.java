package dto.request;

public class ScoreRequestDto {

    private final int gameId;

    public ScoreRequestDto(String gameId) {
        this.gameId = Integer.parseInt(gameId);
    }

    public int getGameId() {
        return gameId;
    }
}
