package domain.piece;

import domain.coordinate.Position;
import domain.squarestatus.Piece;
import domain.type.PieceType;

import java.util.ArrayList;
import java.util.List;

public abstract class SlidingPiece extends Piece {
    protected SlidingPiece(final Color color, final PieceType pieceType) {
        super(color, pieceType);
    }

    protected final List<Position> findPositions(final Position source, final Position target, final Position direction) {
        final List<Position> positions = new ArrayList<>();
        Position position = source.move(direction);

        while (position != target) {
            positions.add(position);
            position = position.move(direction);
        }
        return positions;
    }

    protected final Position findDirection(final Position source, final Position target) {
        final int moveX = getMoveCoordinate(target.diffX(source));
        final int moveY = getMoveCoordinate(target.diffY(source));

        return Position.of(moveX, moveY);
    }

    protected abstract int getMoveCoordinate(final int diffY);
}
