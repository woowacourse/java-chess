package domain.piece;

import domain.Player;
import domain.directions.Direction;
import domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends SpecificLocationPiece {

    private static final int WHITE_START_LINE = 2;
    private static final int BLACK_START_LINE = 7;

    private final List<Direction> directions;
    private final int startLine;

    public Pawn(final Player player) {
        super(player, PieceSymbol.PAWN);
        this.startLine = initStartLine(player);
        this.directions = initDirections(player);
    }

    private int initStartLine(Player player) {
        if (player.equals(Player.BLACK)) {
            return BLACK_START_LINE;
        }
        return WHITE_START_LINE;
    }

    @Override
    protected List<Direction> getDirections() {
        return directions;
    }

    private List<Direction> initDirections(Player player) {
        List<Direction> directions = new ArrayList<>();
        if (player.equals(Player.BLACK)) {
            directions.add(Direction.SOUTHWEST);
            directions.add(Direction.SOUTHEAST);
            directions.add(Direction.SOUTH);
            directions.add(Direction.SOUTH_SOUTH);
            return directions;
        }
        directions.add(Direction.NORTHEAST);
        directions.add(Direction.NORTHWEST);
        directions.add(Direction.NORTH);
        directions.add(Direction.NORTH_NORTH);
        return directions;
    }

    @Override
    protected List<Position> calculateAvailablePosition(final Position source,
        final Direction direction) {
        validateMoveTwoSpace(source, direction);
        List<Position> positions = generateTwoSpaceMoveRoute(source, direction);
        if (checkOverRange(source, direction)) {
            positions.add(createDirectionPosition(source, direction));
        }
        return positions;
    }

    private void validateMoveTwoSpace(Position source, Direction direction) {
        if (!isFirstMove(source) && isTwoSpaceMoveDirection(direction)) {
            throw new IllegalArgumentException("[ERROR] Pawn은 처음 이동할 경우에만 2칸 이동이 가능합니다.");
        }
    }

    private boolean isFirstMove(final Position source) {
        return source.getRank() == startLine;
    }

    private boolean isTwoSpaceMoveDirection(final Direction direction) {
        return direction.equals(Direction.SOUTH_SOUTH) || direction.equals(Direction.NORTH_NORTH);
    }

    private List<Position> generateTwoSpaceMoveRoute(final Position source,
        final Direction direction) {
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

    @Override
    public boolean isPawn() {
        return true;
    }
}
