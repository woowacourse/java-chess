package domain.piece;

import domain.coordinate.Position;
import domain.coordinate.Route;

import java.util.ArrayList;
import java.util.List;

public final class Rook extends Piece {

    public Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        validateMovable(source, target);

        int moveX = getMoveCoordinate(target.diffX(source));
        int moveY = getMoveCoordinate(target.diffY(source));

        return new Route(findPositions(source, target, moveX, moveY));
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        return source.isStraight(target);
    }


    public int getMoveCoordinate(final int diff) {
        return Integer.compare(diff, 0);
    }

    private List<Position> findPositions(final Position source, final Position target, final int moveX, final int moveY) {
        List<Position> positions = new ArrayList<>();
        Position position = source.move(moveX, moveY);

        while (position != target) {
            positions.add(position);
            position = position.move(moveX, moveY);
        }
        return positions;
    }
}
