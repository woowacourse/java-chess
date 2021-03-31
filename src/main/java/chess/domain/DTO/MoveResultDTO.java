package chess.domain.DTO;

public class MoveResultDTO {

    boolean isMove;
    boolean runningGame;

    public MoveResultDTO(boolean isMove, boolean runningGame) {
        this.isMove = isMove;
        this.runningGame = runningGame;
    }

    public boolean isRunningGame() {
        return runningGame;
    }

    public boolean isMove() {
        return isMove;
    }
}
