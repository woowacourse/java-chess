package domain.piece.abstractpiece;

import domain.piece.Color;
import domain.position.Position;
import domain.position.Route;
import domain.squarestatus.Piece;
import domain.type.PieceType;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return Stream.iterate(source.move(direction), position -> !position.equals(target), position -> position.move(direction))
                .collect(Collectors.toList());
    }

    private Position findDirection(final Position source, final Position target) {
        final int moveX = getMoveCoordinate(target.diffX(source));
        final int moveY = getMoveCoordinate(target.diffY(source));

        return Position.of(moveX, moveY);
    }

}
