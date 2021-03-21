package domain;

import domain.piece.*;

import java.util.ArrayList;
import java.util.List;

public class PieceFactory {
    public static List<Piece> createPieces() {
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(Bishop.initialBishopPieces());
        pieces.addAll(King.initialKingPieces());
        pieces.addAll(Queen.initialQueenPieces());
        pieces.addAll(Knight.initialKnightPieces());
        pieces.addAll(Pawn.initialPawnPieces());
        pieces.addAll(Rook.initialRookPieces());
        return pieces;
    }
}
