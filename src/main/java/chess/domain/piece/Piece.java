package chess.domain.piece;

import chess.domain.board.Square;

public abstract class Piece {
    private final Camp camp;

    protected Piece(Camp camp) {
        this.camp = camp;
    }

    public boolean isWhite() {
        return this.camp == Camp.WHITE;
    }

    public boolean isNotSameCamp(Piece otherPiece) {
        return camp != otherPiece.camp;
    }

    public boolean isOpposite(Piece otherPiece) {
        return camp.isOpposite(otherPiece.camp);
    }

    public boolean isOpposite(Camp otherCamp) {
        return camp.isOpposite(otherCamp);
    }

    public abstract boolean canMove(Square source, Square target);

    public abstract PieceType getPieceType();
}
