package chess.domain.piece;

public class PieceFactory {

    public static Piece createPawn(PieceColor pieceColor) {
        return new Piece(PieceType.PAWN, pieceColor);
    }

    public static Piece createRook(PieceColor pieceColor) {
        return new Piece(PieceType.ROOK, pieceColor);
    }

    public static Piece createNight(PieceColor pieceColor) {
        return new Piece(PieceType.KNIGHT, pieceColor);
    }

    public static Piece createBishop(PieceColor pieceColor) {
        return new Piece(PieceType.BISHOP, pieceColor);
    }

    public static Piece createQueen(PieceColor pieceColor) {
        return new Piece(PieceType.QUEEN, pieceColor);
    }

    public static Piece createKing(PieceColor pieceColor) {
        return new Piece(PieceType.KING, pieceColor);
    }
}
