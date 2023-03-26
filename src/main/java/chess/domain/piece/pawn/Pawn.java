package chess.domain.piece.pawn;

import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;
import chess.domain.piece.property.Kind;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Set;

public final class Pawn extends Piece {

    private final PawnMoveStrategy pawnMoveStrategy;

    public Pawn(final Color color) {
        super(color);
        this.pawnMoveStrategy = PawnMoveStrategy.from(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        return pawnMoveStrategy.computePath(source, target);
    }

    @Override
    public boolean canMove(final Map<Position, Boolean> isEmptyPosition, final Position source, final Position target) {
        if (source.isFileEquals(target)) {
            return isEmptyPosition.keySet()
                    .stream()
                    .allMatch(isEmptyPosition::get);
        }
        return !isEmptyPosition.get(target);
    }

    @Override
    public Kind getKind() {
        return Kind.PAWN;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
