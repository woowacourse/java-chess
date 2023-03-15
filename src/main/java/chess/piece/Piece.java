package chess.piece;

import chess.Square;

public abstract class Piece {
    private final Camp camp;

    public Piece(Camp camp) {
        this.camp = camp;
    }

    abstract public PieceType getPieceType();

    abstract public boolean canMove(Square source, Square target);

    public boolean isWhite() {
        return this.camp == Camp.WHITE;
    }

    public boolean isNotSameCamp(Piece otherPiece) {
        return camp != otherPiece.camp;
    }
}
