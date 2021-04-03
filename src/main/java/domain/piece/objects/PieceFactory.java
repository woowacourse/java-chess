package domain.piece.objects;

import domain.piece.position.Position;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {
    private PieceFactory() {

    }

    public static Map<Position, Piece> createPieces() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(Bishop.initialBishopPieces());
        pieces.putAll(King.initialKingPieces());
        pieces.putAll(Queen.initialQueenPieces());
        pieces.putAll(Knight.initialKnightPieces());
        pieces.putAll(Pawn.initialPawnPieces());
        pieces.putAll(Rook.initialRookPieces());
        return pieces;
    }

    public static Piece findPiece(String pieceName) {
        if (pieceName.equals("P") || pieceName.equals("p")) {
            return Pawn.of(pieceName, Character.isUpperCase(pieceName.charAt(0)));
        }

        if (pieceName.equals("B") || pieceName.equals("b")) {
            return Bishop.of(pieceName, Character.isUpperCase(pieceName.charAt(0)));
        }

        if (pieceName.equals("K") || pieceName.equals("k")) {
            return King.of(pieceName, Character.isUpperCase(pieceName.charAt(0)));
        }

        if (pieceName.equals("N") || pieceName.equals("n")) {
            return Pawn.of(pieceName, Character.isUpperCase(pieceName.charAt(0)));
        }

        if (pieceName.equals("Q") || pieceName.equals("q")) {
            return Pawn.of(pieceName, Character.isUpperCase(pieceName.charAt(0)));
        }

        return Rook.of(pieceName, Character.isUpperCase(pieceName.charAt(0)));
    }
}
