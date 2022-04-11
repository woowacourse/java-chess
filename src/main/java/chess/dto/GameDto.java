package chess.dto;

public class GameDto {

    private final String gameId;

    public GameDto(final String gameId) {
        this.gameId = gameId;
    }

    public Long getGameId() {
        return Long.valueOf(gameId);
    }
}
