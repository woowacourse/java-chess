package chess.model.piece;

import chess.model.Position;

public abstract class Piece implements MoveStrategy {

    protected final PieceType type;

    protected Piece(PieceType pieceType) {
        this.type = pieceType;
    }

    public static Piece from(PieceType pieceType) {
        if (PieceType.BLACK_PAWN.equals(pieceType)) {
            return new Pawn(pieceType);
        }
        if (PieceType.BLACK_ROOK.equals(pieceType)) {
            return new Rook(pieceType);
        }
        if (PieceType.BLACK_KNIGHT.equals(pieceType)) {
            return new Knight(pieceType);
        }
        if (PieceType.BLACK_BISHOP.equals(pieceType)) {
            return new Bishop(pieceType);
        }
        if (PieceType.BLACK_QUEEN.equals(pieceType)) {
            return new Queen(pieceType);
        }
        if (PieceType.BLACK_KING.equals(pieceType)) {
            return new King(pieceType);
        }
        if (PieceType.WHITE_PAWN.equals(pieceType)) {
            return new Pawn(pieceType);
        }
        if (PieceType.WHITE_ROOK.equals(pieceType)) {
            return new Rook(pieceType);
        }
        if (PieceType.WHITE_KNIGHT.equals(pieceType)) {
            return new Knight(pieceType);
        }
        if (PieceType.WHITE_BISHOP.equals(pieceType)) {
            return new Bishop(pieceType);
        }
        if (PieceType.WHITE_QUEEN.equals(pieceType)) {
            return new Queen(pieceType);
        }
        if (PieceType.WHITE_KING.equals(pieceType)) {
            return new King(pieceType);
        }
        return new None(pieceType);
    }

    protected final int calculateRowDifference(Position source, Position target) {
        return target.getRow() - source.getRow();
    }

    protected final int calculateColumnDifference(Position source, Position target) {
        return target.getColumn() - source.getColumn();
    }

    public boolean isNone() {
        return type == PieceType.NONE;
    }

    public boolean isSameColorBy(int turnCount) {
        return type.isSameColor(turnCount);
    }

    @Override
    public String toString() {
        return type.getDisplayName();
    }
}
