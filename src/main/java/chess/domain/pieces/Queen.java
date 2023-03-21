package chess.domain.pieces;

import chess.domain.board.Position;
import chess.domain.strategy.Vector;
import java.util.List;

public class Queen extends Piece {

    private static final int MOVE_MAX_RANGE = 8;
    private static final List<Vector> QUEEN_MOVE_VECTOR = List.of(
        Vector.NORTH,
        Vector.EAST,
        Vector.SOUTH,
        Vector.WEST,
        Vector.NORTH_EAST,
        Vector.NORTH_WEST,
        Vector.SOUTH_EAST,
        Vector.SOUTH_WEST
    );

    public Queen(final Team team) {
        super(team);
    }

    @Override
    public void canMove(final Position source, final Position destination) {
        validateMove(source, destination);
    }

    private void validateMove(final Position source, final Position destination) {
        validateMoveDirection(source, destination);
        validateRangeOfMove(source, destination);
    }

    private void validateMoveDirection(final Position source, final Position destination) {
        QUEEN_MOVE_VECTOR.stream()
            .filter(vector -> vector.isSameDirection(source, destination))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("QUEEN은 대각선으로만 움직일 수 있습니다."));
    }

    private void validateRangeOfMove(final Position source, final Position destination) {
        int absSubOfCol = Math.abs(destination.calculateDistanceOfCol(source));
        int absSubOfRow = Math.abs(destination.calculateDistanceOfRow(source));
        if (!(absSubOfRow < MOVE_MAX_RANGE && absSubOfCol < MOVE_MAX_RANGE)) {
            throw new IllegalArgumentException("QUEEN의 이동범위는 최대 8칸 입니다.");
        }
    }
}
