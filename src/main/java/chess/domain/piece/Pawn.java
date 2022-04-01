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
    protected Direction findValidDirection(final Position current, final Position target, final Direction direction) {
        if (isFirstMove(current.getRow())) {
            validateRange(current, target, POSSIBLE_INITIAL_DISTANCE);
            return direction;
        }
        validateRange(current, target, POSSIBLE_DISTANCE);
        return direction;
    }

    @Override
    protected List<Direction> getPossibleDirection() {
        if (getColor() == Color.BLACK) {
            return BLACK_POSSIBLE_DIRECTIONS;
        }
        return WHITE_POSSIBLE_DIRECTIONS;
    }

    private boolean isFirstMove(final Row row) {
        return (row == WHITE_INITIAL_ROW && getColor() == Color.WHITE)
                || (row == BLACK_INITIAL_ROW && getColor() == Color.BLACK);
    }

}
