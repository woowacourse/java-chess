package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;

public class Queen extends Piece{
    private static final String QUEEN_INITIAL = "Q";

    public Queen(Side side) {
        super(side, QUEEN_INITIAL);
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
