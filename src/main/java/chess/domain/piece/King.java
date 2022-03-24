package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.List;

import chess.domain.board.Position;

public class King extends Piece {

    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(E, S, W, N, NE, SE, SW, NW);
    private static final int POSSIBLE_DISTANCE = 1;

    public King(final Color color) {
        super("king", color);
    }

    @Override
    public Direction findValidDirection(Position current, Position target) {
        int rowDifference = target.calculateRowDifference(current);
        int columnDifference = target.calculateColumnDifference(current);
        Direction direction = Direction.calculate(rowDifference, columnDifference);
        validateDirection(direction);
        validateRange(rowDifference, columnDifference);
        return direction;
    }

    private void validateDirection(Direction direction) {
        if (!POSSIBLE_DIRECTIONS.contains(direction)) {
            throw new IllegalArgumentException("진행할 수 없는 방향입니다.");
        }
    }

    private void validateRange(int rowDifference, int columnDifference) {
        if (isInvalidRange(rowDifference, columnDifference)) {
            throw new IllegalArgumentException("진행할 수 없는 위치입니다.");
        }
    }

    private boolean isInvalidRange(int rowDifference, int columnDifference) {
        return Math.abs(rowDifference) > POSSIBLE_DISTANCE || Math.abs(columnDifference) > POSSIBLE_DISTANCE;
    }
}
