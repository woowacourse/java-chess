package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;

public class Blank extends Piece {
    private static final String BLANK_INITIAL = ".";

    public Blank() {
        super(Side.NONE, BLANK_INITIAL);
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
