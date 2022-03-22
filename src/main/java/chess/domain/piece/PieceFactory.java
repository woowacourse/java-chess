package chess.domain.piece;

public class PieceFactory {

    public static Pawn createPawn(PieceColor pieceColor) {
        return new Pawn(pieceColor);
    }

    public static Rook createRook(PieceColor pieceColor) {
        return new Rook(pieceColor);
    }

    public static Night createNight(PieceColor pieceColor) {
        return new Night(pieceColor);
    }

    public static Bishop createBishop(PieceColor pieceColor) {
        return new Bishop(pieceColor);
    }

    public static Queen createQueen(PieceColor pieceColor) {
        return new Queen(pieceColor);
    }

    public static King createKing(PieceColor pieceColor) {
        return new King(pieceColor);
    }
}
