package chess.dto;

public class GameDto {
    private final int gameId;
    private final String turn;

    public GameDto(int gameId, String turn) {
        this.gameId = gameId;
        this.turn = turn;
    }

    public int getGameId() {
        return gameId;
    }

    public String getTurn() {
        return turn;
    }
}
