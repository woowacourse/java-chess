package chess.domain.pieces;

import chess.domain.board.Position;
import chess.domain.direction.Route;
import chess.domain.direction.Direction;
import java.util.List;

public class King extends Piece {

    private static final int MOVE_MAX_RANGE = 1;
    private static final List<Direction> KING_MOVE_DIRECTION = List.of(
        Direction.NORTH,
        Direction.EAST,
        Direction.SOUTH,
        Direction.WEST,
        Direction.NORTH_EAST,
        Direction.NORTH_WEST,
        Direction.SOUTH_EAST,
        Direction.SOUTH_WEST
    );

    public King(final Team team) {
        super(team, PieceType.KING);
    }

    @Override
    public void canMove(final Position source, final Position destination, boolean isAttack) {
        validateMoveDirection(source, destination);
        validateRangeOfMove(source, destination);
    }

    @Override
    public Route generateRoute(final Position source, final Position destination) {
        Direction direction = findDirection(source, destination);
        return Route.generateRouteFromOtherPiece(direction, source, destination);
    }

    private void validateMoveDirection(final Position source, final Position destination) {
        KING_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("KING은 팔방으로 움직일 수 있습니다."));
    }

    private void validateRangeOfMove(final Position source, final Position destination) {
        int absSubOfCol = Math.abs(destination.calculateDistanceOfCol(source));
        int absSubOfRow = Math.abs(destination.calculateDistanceOfRow(source));
        if (!(absSubOfRow <= MOVE_MAX_RANGE && absSubOfCol <= MOVE_MAX_RANGE)) {
            throw new IllegalArgumentException("KING의 이동범위는 최대 1칸 입니다.");
        }
    }

    private Direction findDirection(final Position source, final Position destination) {
        return KING_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination))
            .findFirst()
            .get();
    }
}
