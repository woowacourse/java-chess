package chess.domain.piece.ranged;

import static chess.domain.piece.Direction.E;
import static chess.domain.piece.Direction.N;
import static chess.domain.piece.Direction.NE;
import static chess.domain.piece.Direction.NW;
import static chess.domain.piece.Direction.S;
import static chess.domain.piece.Direction.SE;
import static chess.domain.piece.Direction.SW;
import static chess.domain.piece.Direction.W;

import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.List;

public class King extends RangedPiece {

    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(E, S, W, N, NE, SE, SW, NW);
    private static final int POSSIBLE_DISTANCE = 1;

    public King(Color color) {
        super(PieceType.KING, color);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    protected void validateDirection(Direction direction) {
        if (!POSSIBLE_DIRECTIONS.contains(direction)) {
            throw new IllegalArgumentException(Piece.INVALID_DIRECTION);
        }
    }

    @Override
    protected void validateRange(int columnDifference, int rowDifference) {
        if (isInvalidRange(columnDifference, rowDifference)) {
            throw new IllegalArgumentException(Piece.INVALID_POSITION);
        }
    }

    private boolean isInvalidRange(int columnDifference, int rowDifference) {
        return Math.abs(columnDifference) > POSSIBLE_DISTANCE || Math.abs(rowDifference) > POSSIBLE_DISTANCE;
    }
}
