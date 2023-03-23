package chess.piece;

import chess.board.Position;

public abstract class Pawn extends Piece {

    protected Pawn(final Team team) {
        super(team, PieceType.PAWN);
    }

    @Override
    public abstract boolean isMovable(final Position from, final Position to, final Piece toPiece);

    protected void validateStay(final Position from, final Position to) {
        if (from.isSamePosition(to)) {
            throw new IllegalArgumentException("제자리로는 움직일 수 없습니다.");
        }
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
