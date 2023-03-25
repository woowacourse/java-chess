package chess.domain.dto;

public class GameDto {

    private final String gameId;
    private final String createdAt;

    public GameDto(String gameId, String createdAt) {
        this.gameId = gameId;
        this.createdAt = createdAt;
    }

    public String getGameId() {
        return gameId;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
