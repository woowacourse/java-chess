package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.List;

public class Knight extends Piece {
    private static final String KNIGHT_INITIAL = "N";
    private static final double KNIGHT_SCORE = 2.5;

    public Knight(Side side) {
        super(side, KNIGHT_INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        if (Math.abs(rowDifference) == 2 && Math.abs(columnDifference) == 1) {
            return true;
        }
        return Math.abs(rowDifference) == 1 && Math.abs(columnDifference) == 2;
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        return Collections.emptyList();
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return KNIGHT_SCORE;
    }
}
