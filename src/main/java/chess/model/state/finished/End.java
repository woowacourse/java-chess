package chess.model.state.finished;

import chess.model.board.GameResult;

public final class End extends Finished {

    public End() {
        super();
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public GameResult getScore() {
        throw new IllegalArgumentException("[ERROR] 게임이 종료되어 점수를 계산 할 수 없습니다.");
    }
}
