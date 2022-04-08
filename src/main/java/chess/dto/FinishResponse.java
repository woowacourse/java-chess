package chess.dto;

public class FinishResponse {

    private final int gameNumber;

    public FinishResponse(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    public int getGameNumber() {
        return gameNumber;
    }
}
