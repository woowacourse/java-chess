package chess.domain.piece;

import chess.domain.board.Square;

public abstract class Piece {
    private final Camp camp;

    public Piece(final Camp camp) {
        this.camp = camp;
    }

    abstract public PieceType getPieceType();

    abstract public boolean canMove(final Square source, final Square target);

    public boolean isWhite() {
        return this.camp == Camp.WHITE;
    }

    public boolean isNotSameCamp(final Piece otherPiece) {
        return camp != otherPiece.camp;
    }

    public boolean isCamp(final Camp camp) {
        return this.camp.equals(camp);
    }

    public boolean isSamePieceType(final PieceType pieceType) {
        return getPieceType().equals(pieceType);
    }

    public boolean isOpposite(final Piece otherPiece) {
        return camp.isOpposite(otherPiece.camp);
    }

    public boolean isOpposite(final Camp otherCamp) {
        return camp.isOpposite(otherCamp);
    }

    public double getScore() {
        return this.getPieceType().getScore();
    }
}
