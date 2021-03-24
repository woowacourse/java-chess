package chess.domain.piece;

import chess.domain.game.Side;
import chess.domain.gamestate.GamePieceExceptPawn;
import chess.domain.position.Position;
import java.util.List;

public final class Bishop extends GamePieceExceptPawn {

    private static final String INITIAL = "B";
    private static final int SCORE = 3;

    public Bishop(Side side) {
        super(side, INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        return isDiagonal(rowDifference, columnDifference);
    }

    private boolean isDiagonal(int rowDifference, int columnDifference) {
        return Math.abs(rowDifference) == Math.abs(columnDifference);
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        return Position.route(from, to);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return SCORE;
    }
}
