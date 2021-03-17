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
    protected List<Position> getRoute() {
        return null;
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        return false;
    }
}
