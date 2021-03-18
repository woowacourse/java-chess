package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;

public class Queen extends Piece {
    private static final String QUEEN_INITIAL = "Q";

    public Queen(Side side) {
        super(side, QUEEN_INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        if (rowDifference == 0 || columnDifference == 0) {
            return true;
        }
        return Math.abs(rowDifference) == Math.abs(columnDifference);
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        return Position.getRoute(from, to);
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
