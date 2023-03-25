package domain.piece.abstractpiece;

import domain.piece.Color;
import domain.position.Position;
import domain.position.Route;
import domain.squarestatus.Piece;
import domain.type.PieceType;

import java.util.ArrayList;
import java.util.List;

public abstract class SlidingPiece extends Piece {

    protected SlidingPiece(final Color color, final PieceType pieceType) {
        super(color, pieceType);
    }

    @Override
    public final Route findRoute(final Position source, final Position target) {
        validateMovable(source, target);
        final Position direction = findDirection(source, target);

        return new Route(findPositions(source, target, direction));
    }

    protected abstract int getMoveCoordinate(final int diffY);

    private List<Position> findPositions(final Position source, final Position target, final Position direction) {
        final List<Position> positions = new ArrayList<>();
        Position position = source.move(direction);

        while (position != target) {
            positions.add(position);
            position = position.move(direction);
        }
        return positions;
    }

    private Position findDirection(final Position source, final Position target) {
        final int moveX = getMoveCoordinate(target.diffX(source));
        final int moveY = getMoveCoordinate(target.diffY(source));

        return Position.of(moveX, moveY);
    }

}
