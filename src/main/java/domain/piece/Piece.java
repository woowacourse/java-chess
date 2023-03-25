package domain.piece;

import domain.game.PieceType;
import view.PieceCategory;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected final Side side;
    protected final PieceType pieceType;

    protected Piece(Side side, PieceType pieceType) {
        this.side = side;
        this.pieceType = pieceType;
    }

    public abstract boolean isMovable(Piece targetPiece, Position sourcePosition, Position targetPosition);

    public abstract List<Position> collectPath(Position sourcePosition, Position targetPosition);

    public abstract boolean isEmptyPiece();

    public abstract PieceCategory getCategory();

    public boolean isSameSideWith(Piece targetPiece) {
        return this.side == targetPiece.side;
    }

    public boolean isOpponentSideWith(Piece targetPiece) {
        if (this.side.equals(Side.WHITE)) {
            return targetPiece.side.equals(Side.BLACK);
        }
        return targetPiece.side.equals(Side.WHITE);
    }

    public boolean isIncorrectTurn(Side currentTurn) {
        return this.side != currentTurn;
    }

    public boolean isSameSide(Side side) {
        return this.side == side;
    }

    public boolean isSameType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public double score() {
        return pieceType.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return side == piece.side && pieceType == piece.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side, pieceType);
    }
}
