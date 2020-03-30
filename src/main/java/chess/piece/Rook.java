package chess.piece;

import chess.coordinate.Vector;

public class Rook extends Piece {

    private static final int SCORE = 5;

    public Rook(final Team team) {
        super(team, SCORE);
    }

    @Override
    protected boolean canReach(Vector vector, Piece targetPiece) {
        return vector.isStraight();
    }
}
