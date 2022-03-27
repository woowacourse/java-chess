package chess.domain.piece;

import static chess.domain.piece.Direction.N;
import static chess.domain.piece.Direction.NE;
import static chess.domain.piece.Direction.NW;
import static chess.domain.piece.Direction.S;
import static chess.domain.piece.Direction.SE;
import static chess.domain.piece.Direction.SW;

import chess.domain.board.Position;
import chess.domain.board.Row;
import java.util.List;

public class Pawn extends Piece {

    private static final List<Direction> WHITE_POSSIBLE_DIRECTIONS = List.of(N, NE, NW);
    private static final List<Direction> BLACK_POSSIBLE_DIRECTIONS = List.of(S, SE, SW);
    private static final int POSSIBLE_DISTANCE = 1;
    private static final int POSSIBLE_INITIAL_DISTANCE = 2;
    private static final Row WHITE_INITIAL_ROW = Row.SECOND;
    private static final Row BLACK_INITIAL_ROW = Row.SEVENTH;

    public Pawn(final Color color) {
        super(PieceType.PAWN, color);
    }

    @Override
    public Direction findValidDirection(final Position current, final Position target) {
        int rowDifference = target.calculateRowDifference(current);
        int columnDifference = target.calculateColumnDifference(current);
        Direction direction = Direction.calculate(rowDifference, columnDifference);
        validateDirection(direction);
        if (isFirstMove(current.getRow())) {
            validateInitialRange(rowDifference, columnDifference);
            return direction;
        }
        validateRange(rowDifference, columnDifference);
        return direction;
    }

    private void validateInitialRange(final int rowDifference, final int columnDifference) {
        if (isValidInitialRange(rowDifference, columnDifference)) {
            throw new IllegalArgumentException(INVALID_POSITION);
        }
    }

    private boolean isValidInitialRange(final int rowDifference, final int columnDifference) {
        return Math.abs(rowDifference) > POSSIBLE_INITIAL_DISTANCE || Math.abs(columnDifference) > POSSIBLE_DISTANCE;
    }

    private void validateDirection(final Direction direction) {
        if (isInvalidDirection(direction)) {
            throw new IllegalArgumentException(INVALID_DIRECTION);
        }
    }

    private boolean isInvalidDirection(final Direction direction) {
        if (getColor() == Color.BLACK) {
            return isInvalidBlackDirection(direction);
        }
        return isInvalidWhiteDirection(direction);
    }

    private boolean isInvalidBlackDirection(final Direction direction) {
        return !BLACK_POSSIBLE_DIRECTIONS.contains(direction);
    }

    private boolean isInvalidWhiteDirection(final Direction direction) {
        return !WHITE_POSSIBLE_DIRECTIONS.contains(direction);
    }

    private boolean isFirstMove(final Row row) {
        return (row == WHITE_INITIAL_ROW && getColor() == Color.WHITE)
                || (row == BLACK_INITIAL_ROW && getColor() == Color.BLACK);
    }

    private void validateRange(final int rowDifference, final int columnDifference) {
        if (isInvalidRange(rowDifference, columnDifference)) {
            throw new IllegalArgumentException(INVALID_POSITION);
        }
    }

    private boolean isInvalidRange(final int rowDifference, final int columnDifference) {
        return Math.abs(rowDifference) > POSSIBLE_DISTANCE || Math.abs(columnDifference) > POSSIBLE_DISTANCE;
    }

}
