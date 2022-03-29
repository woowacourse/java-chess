package domain.piece;

import domain.Player;
import domain.directions.Direction;
import domain.directions.DirectionsGenerator;
import domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends SpecificLocationPiece {

    private final int startLine;

    public Pawn(final Player player, final DirectionsGenerator directionsGenerator,
        final int startLine) {
        super(player, PieceSymbol.PAWN, directionsGenerator);
        this.startLine = startLine;
    }

    @Override
    protected List<Position> calculateAvailablePosition(final Position source, final Direction direction) {
        if (!isFirstMove(source) && isTwoSpaceMoveDirection(direction)) {
            return null;
        }
        List<Position> positions = generateTwoSpaceMoveRoute(source, direction);
        if (checkOverRange(source, direction)) {
            positions.add(createDirectionPosition(source, direction));
        }
        return positions;
    }

    private boolean isFirstMove(final Position source) {
        return source.getRank() == startLine;
    }

    private boolean isTwoSpaceMoveDirection(final Direction direction) {
        return direction.equals(Direction.SOUTH_SOUTH) || direction.equals(Direction.NORTH_NORTH);
    }

    private List<Position> generateTwoSpaceMoveRoute(final Position source, final Direction direction) {
        List<Position> positions = new ArrayList<>();
        if (isTwoSpaceMoveDirection(direction)) {
            positions.add(calculatePawnWayPoint(source, direction));
        }
        return positions;
    }

    private Position calculatePawnWayPoint(final Position source, final Direction direction) {
        Direction addDirection = generateAddDirection(direction);
        if (addDirection != null && checkOverRange(source, addDirection)) {
            return createDirectionPosition(source, addDirection);
        }
        return null;
    }

    private Direction generateAddDirection(final Direction direction) {
        if (direction.equals(Direction.NORTH_NORTH)) {
            return Direction.NORTH;
        }
        if (direction.equals(Direction.SOUTH_SOUTH)) {
            return Direction.SOUTH;
        }
        return null;
    }
}
