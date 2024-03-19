package chess.domain.piece;

import java.util.List;

public class PiecesFactory {
    public static List<Piece> createBlackPieces() {
        return List.of(
                new Rook(Color.BLACK),
                new Knight(Color.BLACK),
                new Bishop(Color.BLACK),
                new Queen(Color.BLACK),
                new King(Color.BLACK),
                new Bishop(Color.BLACK),
                new Knight(Color.BLACK),
                new Rook(Color.BLACK)
        );
    }

    public static List<Piece> createBlackPawns() {
        return List.of(
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK),
                new Pawn(Color.BLACK)
        );
    }

    public static List<Piece> createWhitePawns() {
        return List.of(
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE),
                new Pawn(Color.WHITE)
        );
    }

    public static List<Piece> createEmptyPieces() {
        return List.of(
                new EmptyPiece(),
                new EmptyPiece(),
                new EmptyPiece(),
                new EmptyPiece(),
                new EmptyPiece(),
                new EmptyPiece(),
                new EmptyPiece(),
                new EmptyPiece()
        );
    }

    public static List<Piece> createWhitePieces() {
        return List.of(
                new Rook(Color.WHITE),
                new Knight(Color.WHITE),
                new Bishop(Color.WHITE),
                new Queen(Color.WHITE),
                new King(Color.WHITE),
                new Bishop(Color.WHITE),
                new Knight(Color.WHITE),
                new Rook(Color.WHITE)
        );
    }
}
