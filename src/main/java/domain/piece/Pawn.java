package domain.piece;

import domain.Navigation;
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
    public List<Position> move(Position source, Position target) {
        Direction direction = direction(source, target);
        if (isMoveDirection(direction)) {
            validateMoveTwoSpace(direction, source);
            List<Position> positions = addOneSpaceMovePosition(source, target, direction);
            positions.addAll(new Navigation(source, target).route(direction));
            return positions;
        }
        return new ArrayList<>();
    }

    private Direction changeOneSpaceDirection(Direction direction) {
        if (direction == Direction.NORTH_NORTH) {
            return Direction.NORTH;
        }
        return Direction.SOUTH;
    }

    public List<Position> addOneSpaceMovePosition(Position source, Position target, Direction direction) {
        if (isTwoSpaceMoveDirection(direction)) {
            Direction addDirection = changeOneSpaceDirection(direction);
            return new Navigation(source, target).route(addDirection);
        }
        return new ArrayList<>();
    }

    private boolean isMoveDirection(final Direction direction) {
        return direction == Direction.NORTH
            || direction == Direction.SOUTH
            || direction == Direction.NORTH_NORTH
            || direction == Direction.SOUTH_SOUTH;
    }

    private void validateMoveTwoSpace(final Direction direction, final Position source) {
        if (isTwoSpaceMoveDirection(direction) && !isFirstMove(source)) {
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
        return direction == Direction.SOUTH_SOUTH
            || direction == Direction.NORTH_NORTH;
    }

    @Override
    protected List<Direction> getDirections() {
        if (getPlayer() == Player.BLACK) {
            return BLACK_DIRECTIONS;
        }
        return WHITE_DIRECTIONS;
    }

    @Override
    public double score(final boolean isSeveralPawn) {
        return PieceScore.PAWN.score(isSeveralPawn);
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
