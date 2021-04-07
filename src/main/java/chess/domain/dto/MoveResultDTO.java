package chess.domain.dto;

public class MoveResultDTO {

    boolean isMove;
    boolean runningGame;
    Double whiteScore;
    Double blackScore;
    String winner;

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

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
