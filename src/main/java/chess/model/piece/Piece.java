package chess.model.piece;

public class Piece {

    private final PieceType type;

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
        return new Piece(pieceType);
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
