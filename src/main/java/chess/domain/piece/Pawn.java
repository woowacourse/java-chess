package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.piece.strategy.BlackPawnMoving;
import chess.domain.piece.strategy.Movable;
import chess.domain.piece.strategy.WhitePawnMoving;
import chess.domain.position.Position;

public class Pawn extends Piece {

    private static final String NAME = "p";
    private static final double SCORE = 1;

    public Pawn(final Color color) {
        super(color, NAME);
    }

    private boolean isPawnMoving(final Board board, final Position from, final Position to) {
        Movable movable = getMovingStrategy();

        return movable.isPawnMoving(board, from, to);
    }

    private Movable getMovingStrategy() {
        if (getColor() == Color.WHITE) {
            return new WhitePawnMoving();
        }
        return new BlackPawnMoving();
    }

    @Override
    public void checkMovingRange(final Board board, final Position from, final Position to) {
        if (isPawnMoving(board, from, to)) {
            return;
        }
        throw new IllegalArgumentException("폰은 앞으로 한 칸만 이동할 수 있습니다.");
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
