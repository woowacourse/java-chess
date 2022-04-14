package chess.dto;

public class GameData {
    private static final int EMPTY_CONDITION = -1;

    private final int gameId;
    private final String turn;

    private GameData(int gameId, String turn) {
        this.gameId = gameId;
        this.turn = turn;
    }

    public static GameData of(int gameId, String turn) {
        return new GameData(gameId, turn);
    }

    public static GameData empty() {
        return new GameData(EMPTY_CONDITION, null);
    }

    public int getGameId() {
        return gameId;
    }

    public String getTurn() {
        return turn;
    }

    public boolean isEmpty() {
        return gameId == EMPTY_CONDITION;
    }
}
