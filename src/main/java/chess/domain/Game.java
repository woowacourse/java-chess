package chess.domain;

public class Game {

    private final String gameId;
    private final String createdAt;

    public Game(String gameId, String createdAt) {
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
