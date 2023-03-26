package database.dto;

public class ChessGameDto {

    private final int gameId;
    private final String gameStatus;
    private final String turn;

    public ChessGameDto(int gameId, String gameStatus, String turn) {
        this.gameId = gameId;
        this.gameStatus = gameStatus;
        this.turn = turn;
    }

    public int getGameId() {
        return gameId;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public String getTurn() {
        return turn;
    }
}
