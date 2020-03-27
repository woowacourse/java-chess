package chess.board.piece;

import chess.board.Vector;

public class NotMovedPawn extends AbstractPawn {
    public NotMovedPawn(final Team team) {
        super(team);
    }

    @Override
    protected boolean canMoveTwoStep(final Vector vector, final Piece targetPiece) {
        return vector.isRangeUnderAbsolute(2) && team.isSameDirection(vector.getRankVariation()) && vector.isStraight();
    }

    @Override
    public Piece move() {
        return Pieces.findBy(MovedPawn.class, this.team);
    }
}
