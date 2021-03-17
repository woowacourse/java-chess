package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;

public class Knight extends Piece {
    private static final String KNIGHT_INITIAL = "N";

    public Knight(Side side) {
        super(side, KNIGHT_INITIAL);
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
