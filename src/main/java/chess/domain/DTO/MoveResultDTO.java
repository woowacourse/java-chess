package chess.domain.DTO;

public class MoveResultDTO {

    boolean isMove;
    boolean runningGame;
    Double whiteScore;
    Double blackScore;

    public MoveResultDTO(boolean isMove, boolean runningGame, Double whiteScore,
        Double blackScore) {
        this.isMove = isMove;
        this.runningGame = runningGame;
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public boolean isMove() {
        return isMove;
    }

    public boolean isRunningGame() {
        return runningGame;
    }

    public Double getWhiteScore() {
        return whiteScore;
    }

    public Double getBlackScore() {
        return blackScore;
    }
}
