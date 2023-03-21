package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.KnightDirection;

import java.util.Collections;
import java.util.List;

public final class Knight extends Piece {
    private Knight(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Knight from(final Color color) {
        return new Knight(PieceType.KNIGHT, color);
    }

    @Override
    protected List<Position> createMovablePositions(final Position source, final Position target) {
        final Direction direction = KnightDirection.from(source, target);

        if (isMovable(source, target, direction)) {
            return List.of(source.move(direction));
        }

        return Collections.emptyList();
    }

    private boolean isMovable(final Position source, final Position target, final Direction direction) {
        return source.isRangeOk(direction) && target.equals(source.move(direction));
    }
}
