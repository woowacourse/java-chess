package chess.domain.piece.ranged;

import static chess.domain.piece.Direction.NEE;
import static chess.domain.piece.Direction.NNE;
import static chess.domain.piece.Direction.NNW;
import static chess.domain.piece.Direction.NWW;
import static chess.domain.piece.Direction.SEE;
import static chess.domain.piece.Direction.SSE;
import static chess.domain.piece.Direction.SSW;
import static chess.domain.piece.Direction.SWW;

import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.List;

public class Knight extends RangedPiece {

    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(NNE, NEE, SEE, SSE, SSW, SWW, NWW, NNW);
    private static final int POSSIBLE_DISTANCE = 2;

    public Knight(Color color) {
        super(PieceType.KNIGHT, color);
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
