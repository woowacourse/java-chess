package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Map;
import java.util.Set;

public final class Empty extends Piece {

    private static final String UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE = "수행할 수 없는 명령입니다.";

    public Empty() {
        super(Color.NONE);
    }

    @Override
    protected Set<Position> computePath(final Position source, final Position target) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
    }

    protected boolean canMove(final Map<Position, Boolean> isEmptyPosition, final Position source, final Position target) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Kind getKind() {
        return Kind.EMPTY;
    }
}
