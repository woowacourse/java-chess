package chess.dao;

public class ChessGameEntity {
    private final String gameId;
    private final String data;

    public ChessGameEntity(String gameId, String data) {
        this.gameId = gameId;
        this.data = data;
    }

    public String getGameId() {
        return gameId;
    }

    public String getData() {
        return data;
    }

}
