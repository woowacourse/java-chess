package chess.domain.piece;

import chess.domain.board.Square;

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

    public boolean isCamp(Camp camp) {
        return this.camp.equals(camp);
    }

    public boolean isCamp(Piece piece) {
        return this.camp.equals(piece.camp);
    }

    public boolean isSamePieceType(PieceType pieceType) {
        return getPieceType().equals(pieceType);
    }

    public boolean isOpposite(Piece otherPiece) {
        return camp.isOpposite(otherPiece.camp);
    }

    public boolean isOpposite(Camp otherCamp) {
        return camp.isOpposite(otherCamp);
    }

    public double getScore() {
        return this.getPieceType().getScore();
    }
}
