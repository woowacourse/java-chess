package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;

public class Rook extends Piece {
    private static final String ROOK_INITIAL = "R";
    private static final int ROOK_SCORE = 5;

    public Rook(Side side) {
        super(side, ROOK_INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        return isStraight(rowDifference, columnDifference);
    }

    private boolean isStraight(int rowDifference, int columnDifference) {
        return rowDifference == 0 || columnDifference == 0;
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        return Position.route(from, to);
    }

    @Override
    public double score() {
        return ROOK_SCORE;
    }
}
