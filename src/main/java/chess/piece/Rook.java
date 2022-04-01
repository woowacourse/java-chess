package chess.piece;

import chess.game.Position;

public class Rook extends AbstractPiece {

    private static final double SCORE = 5;

    public Rook(final Color color) {
        super(Name.ROOK, color);
    }

    @Override
    public boolean canMove(final Position from, final Position to) {
        return canHorizontalAndVerticalMove(from, to);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
