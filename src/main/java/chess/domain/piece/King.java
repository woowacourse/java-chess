package chess.domain.piece;

import static chess.domain.piece.Direction.E;
import static chess.domain.piece.Direction.N;
import static chess.domain.piece.Direction.NE;
import static chess.domain.piece.Direction.NW;
import static chess.domain.piece.Direction.S;
import static chess.domain.piece.Direction.SE;
import static chess.domain.piece.Direction.SW;
import static chess.domain.piece.Direction.W;

import chess.domain.board.Position;
import java.util.List;

public class King extends Piece {

    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(E, S, W, N, NE, SE, SW, NW);
    private static final int POSSIBLE_DISTANCE = 1;

    public King(final Color color) {
        super(PieceType.KING, color);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Direction findValidDirection(final Position current, final Position target) {
        final int columnDifference = target.calculateColumnDifference(current);
        final int rowDifference = target.calculateRowDifference(current);
        final Direction direction = Direction.calculate(columnDifference, rowDifference);
        validateDirection(direction);
        validateRange(columnDifference, rowDifference);
        return direction;
    }

    private void validateDirection(final Direction direction) {
        if (!POSSIBLE_DIRECTIONS.contains(direction)) {
            throw new IllegalArgumentException(INVALID_DIRECTION);
        }
    }

    private void validateRange(final int columnDifference, final int rowDifference) {
        if (isInvalidRange(columnDifference, rowDifference)) {
            throw new IllegalArgumentException(INVALID_POSITION);
        }
    }

    private boolean isInvalidRange(final int columnDifference, final int rowDifference) {
        return Math.abs(columnDifference) > POSSIBLE_DISTANCE || Math.abs(rowDifference) > POSSIBLE_DISTANCE;
    }
}
