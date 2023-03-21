package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.normal.Piece;
import chess.domain.piece.property.Color;
import chess.domain.piece.property.Kind;

import java.util.Map;
import java.util.Set;

public final class Empty extends Piece {

    public Empty() {
        super(Color.NONE);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        throw new UnsupportedOperationException("수행할 수 없는 명령입니다.");
    }

    @Override
    public boolean canMove(final Map<Position, Boolean> isEmptyPosition, final Position source, final Position target) {
        throw new UnsupportedOperationException("수행할 수 없는 명령입니다.");
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
