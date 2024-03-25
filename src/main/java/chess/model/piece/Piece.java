package chess.model.piece;

import chess.model.Position;

public abstract class Piece{

    protected final PieceType type;
    protected final Color color;

    protected Piece(PieceType pieceType, Color color) {
        this.type = pieceType;
        this.color = color;
    }

    public abstract boolean canMove(Position source, Position target);

    public abstract boolean canAttack(Position source, Position target);

    public abstract boolean canJump();

    public static Piece from(PieceType pieceType, Color pieceColor) {
        if (pieceType == PieceType.PAWN) {
            return new Pawn(pieceType, pieceColor);
        }
        if (pieceType == PieceType.ROOK) {
            return new Rook(pieceType, pieceColor);
        }
        if (pieceType == PieceType.KNIGHT) {
            return new Knight(pieceType, pieceColor);
        }
        if (pieceType == PieceType.BISHOP) {
            return new Bishop(pieceType, pieceColor);
        }
        if (pieceType == PieceType.QUEEN) {
            return new Queen(pieceType, pieceColor);
        }
        if (pieceType == PieceType.KING) {
            return new King(pieceType, pieceColor);
        }
        return new None(pieceType);
    }

    protected final int calculateRowDifference(Position source, Position target) {
        return target.getRow() - source.getRow();
    }

    protected final int calculateColumnDifference(Position source, Position target) {
        return target.getColumn() - source.getColumn();
    }

    public boolean isEnemy(Color turn) {
        return !color.isSameColor(turn);
    }

    public boolean isAlly(Color turn) {
        return color.isSameColor(turn);
    }

    public boolean isWhite() {
        return color.isWhite();
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    @Override
    public String toString() {
        return type.getDisplayName(color);
    }
}
