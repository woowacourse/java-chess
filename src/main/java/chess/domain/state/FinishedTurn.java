package chess.domain.state;

import chess.domain.piece.Pieces;
import chess.domain.position.Source;
import chess.domain.position.Target;

public class FinishedTurn extends Turn {
    public FinishedTurn(final Pieces pieces) {
        super(pieces);
    }

    @Override
    public final State move(final Source source, final Target target, final State anotherState) {
        throw new UnsupportedOperationException("한 턴이 끝난 경우에는 기물을 움직일 수 없습니다.");
    }

    @Override
    public final boolean isFinishedTurn() {
        return true;
    }

    @Override
    public State toRunningTurn(final State anotherState) {
        if (anotherState.isFinishedTurn()) {
            return new RunningTurn(pieces());
        }
        throw new IllegalStateException("상대 턴 차례입니다.");
    }
}
