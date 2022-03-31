package domain.piece;

import domain.Player;
import domain.direction.Direction;
import domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends SpecificLocationPiece {

    private static final int WHITE_START_LINE = 2;
    private static final int BLACK_START_LINE = 7;

    private static final List<Direction> BLACK_DIRECTIONS = List.of(
        Direction.SOUTHWEST,
        Direction.SOUTHEAST,
        Direction.SOUTH,
        Direction.SOUTH_SOUTH
    );
    private static final List<Direction> WHITE_DIRECTIONS = List.of(
        Direction.SOUTHWEST,
        Direction.NORTHEAST,
        Direction.NORTHWEST,
        Direction.NORTH,
        Direction.NORTH_NORTH
    );

    public Pawn(final Player player) {
        super(player, PieceSymbol.PAWN);
    }

    @Override
    protected List<Position> calculateAvailablePosition(final Position source,
        final Direction direction) {
        List<Position> positions = generateTwoSpaceMoveRoute(source, direction);
        if (checkOverRange(source, direction)) {
            positions.add(createPositionByDirection(source, direction));
        }
        return positions;
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
            return createPositionByDirection(source, addDirection);
        }
        return null;
    }

    private Direction generateAddDirection(final Direction direction) {
        if (direction == Direction.NORTH_NORTH) {
            return Direction.NORTH;
        }
        if (direction == Direction.SOUTH_SOUTH) {
            return Direction.SOUTH;
        }
        return null;
    }

    @Override
    protected List<Direction> getDirections() {
        if (getPlayer() == Player.BLACK) {
            return BLACK_DIRECTIONS;
        }
        return WHITE_DIRECTIONS;
    }

    @Override
    public List<Position> getAvailablePositions(final Position source, final Position target) {
        Direction direction = findDirection(target);
        List<Position> positions = getAvailableMovePosition().get(direction);
        if (isTwoSpaceMoveDirection(direction)) {
            validateMoveTwoSpace(source);
            return positions;
        }
        int index = positions.indexOf(target);
        return positions.subList(0, index);
    }

    private void validateMoveTwoSpace(final Position source) {
        if (!isFirstMove(source)) {
            throw new IllegalArgumentException("[ERROR] Pawn은 처음 이동할 경우에만 2칸 이동이 가능합니다.");
        }
    }

    private boolean isFirstMove(final Position source) {
        return source.getRank() == getStartLine(getPlayer());
    }

    private int getStartLine(final Player player) {
        if (player == Player.BLACK) {
            return BLACK_START_LINE;
        }
        return WHITE_START_LINE;
    }

    private boolean isTwoSpaceMoveDirection(final Direction direction) {
        return direction == Direction.SOUTH_SOUTH || direction == Direction.NORTH_NORTH;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
