package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Set;

public final class Queen extends Normal {

    public Queen(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        try{
            return source.computeCrossOrDiagonalPath(target);
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public Kind getKind() {
        return Kind.QUEEN;
    }
}
