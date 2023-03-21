package chess.domain.pieces;

import chess.domain.board.Position;
import chess.domain.strategy.Route;
import chess.domain.strategy.Vector;
import java.util.List;

public class Rook extends Piece {

    private static final int MOVE_MAX_RANGE = 8;
    private static final List<Vector> ROOK_MOVE_VECTOR = List.of(
        Vector.NORTH,
        Vector.EAST,
        Vector.WEST,
        Vector.SOUTH
    );

    public Rook(final Team team) {
        super(team);
    }

    @Override
    public void canMove(final Position source, final Position destination, boolean isAttack) {
        validateMoveDirection(source, destination);
        validateRangeOfMove(source, destination);
    }

    @Override
    public Route generateRoute(final Position source, final Position destination) {
        Vector direction = findDirection(source, destination);
        return Route.generateRouteFromOtherPiece(direction, source, destination);
    }

    private void validateMoveDirection(final Position source, final Position destination) {
        ROOK_MOVE_VECTOR.stream()
            .filter(vector -> vector.isSameDirection(source, destination))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("ROOK은 사방으로만 움직일 수 있습니다."));
    }

    private void validateRangeOfMove(final Position source, final Position destination) {
        int absSubOfCol = Math.abs(destination.calculateDistanceOfCol(source));
        int absSubOfRow = Math.abs(destination.calculateDistanceOfRow(source));
        if (!(absSubOfRow < MOVE_MAX_RANGE && absSubOfCol < MOVE_MAX_RANGE)) {
            throw new IllegalArgumentException("ROOK의 이동범위는 최대 8칸 입니다.");
        }
    }

    private Vector findDirection(final Position source, final Position destination) {
        return ROOK_MOVE_VECTOR.stream()
            .filter(vector -> vector.isSameDirection(source, destination))
            .findFirst()
            .get();
    }
}
