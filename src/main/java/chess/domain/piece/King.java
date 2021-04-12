package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.List;

public class King extends Piece {
    private static final String KING_INITIAL = "K";
    private static final int KING_SCORE = 0;

    public King(Side side) {
        super(side, KING_INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        return Math.abs(rowDifference) == 1 || Math.abs(columnDifference) == 1;
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        return Collections.emptyList();
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double score() {
        return KING_SCORE;
    }
}
