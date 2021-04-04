package chess.dto;

public class ChessGameStatusDto {
    private final String turn;
    private final boolean isFinish;

    public ChessGameStatusDto(String turn, boolean isFinish) {
        this.turn = turn;
        this.isFinish = isFinish;
    }

    public String getTurn() {
        return turn;
    }

    public boolean isFinish() {
        return isFinish;
    }
}
