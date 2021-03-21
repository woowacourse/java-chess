package domain.piece.objects;

import domain.piece.Position;
import domain.piece.objects.*;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {
    public static Map<Position, Piece> createPieces() {
        Map<Position, Piece> pieces = new HashMap<Position, Piece>();
        pieces.putAll(Bishop.initialBishopPieces());
        pieces.putAll(King.initialKingPieces());
        pieces.putAll(Queen.initialQueenPieces());
        pieces.putAll(Knight.initialKnightPieces());
        pieces.putAll(Pawn.initialPawnPieces());
        pieces.putAll(Rook.initialRookPieces());
        return pieces;
    }
}
