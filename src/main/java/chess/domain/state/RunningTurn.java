package chess.domain.state;

import chess.domain.piece.Pieces;
import chess.domain.position.Source;
import chess.domain.position.Target;

public class RunningTurn extends Turn {
    public RunningTurn(final Pieces pieces) {
        super(pieces);
    }

    @Override
    public State move(final Source source, final Target target, final State anotherState) {
        if (!anotherState.isFinishedTurn()) {
            throw new IllegalStateException("상대의 턴이 아직 끝나지 않았습니다.");
        }
        Pieces pieces = pieces();
        pieces.move(source, target, anotherState.pieces());
        return new FinishedTurn(pieces);
    }

    @Override
    public boolean isFinishedTurn() {
        return false;
    }

    @Override
    public State toRunningTurn(final State anotherState) {
        throw new UnsupportedOperationException("이미 한 턴이 진행중입니다.");
    }
}
