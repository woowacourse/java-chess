package chess.domain.piece;

import chess.domain.game.Side;
import chess.domain.position.Position;
import java.util.List;

public final class Queen extends GamePieceExceptPawn {

    private static final String INITIAL = "Q";
    private static final int SCORE = 9;

    public Queen(Side side) {
        super(side, INITIAL);
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference, Piece targetPiece) {
        if (isStraight(rowDifference, columnDifference)) {
            return true;
        }
        return isDiagonal(rowDifference, columnDifference);
    }

    private boolean isDiagonal(int rowDifference, int columnDifference) {
        return Math.abs(rowDifference) == Math.abs(columnDifference);
    }

    private boolean isStraight(int rowDifference, int columnDifference) {
        return rowDifference == 0 || columnDifference == 0;
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
