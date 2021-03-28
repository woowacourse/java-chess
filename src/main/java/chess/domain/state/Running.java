package chess.domain.state;

import chess.domain.piece.Pieces;
import chess.domain.position.Source;
import chess.domain.position.Target;

public class Running extends Turn {
    public Running(final Pieces pieces) {
        super(pieces);
    }

    @Override
    public State move(final Source source, final Target target, final State anotherState) {
        if (!anotherState.isFinish()) {
            throw new IllegalStateException("상대턴이 아직 끝나지 않았습니다.");
        }
        Pieces pieces = pieces();
        pieces.move(source, target, anotherState.pieces());
        return new Finished(pieces);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public State toRunningState(final State anotherState) {
        throw new UnsupportedOperationException("이미 진행중인 상태입니다.");
    }
}
