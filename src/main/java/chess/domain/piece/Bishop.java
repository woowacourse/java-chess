package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;

public class Bishop extends Piece {
    private static final String BISHOP_INITIAL = "B";

    public Bishop(Side side) {
        super(side, BISHOP_INITIAL);
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        return Position.getRoute(from, to);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        return Math.abs(rowDifference) == Math.abs(columnDifference);
    }
}
