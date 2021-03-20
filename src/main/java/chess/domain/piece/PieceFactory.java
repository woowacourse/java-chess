package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public class PieceFactory {
    public PieceFactory() {
    }

    public static List<Piece> initialPieces() {
        List<Piece> initialPieces = new ArrayList<>();
        initialPieces.addAll(King.initialKings());
        initialPieces.addAll(Knight.initialKnights());
        initialPieces.addAll(Queen.initialQueens());
        initialPieces.addAll(Rook.initialRooks());
        initialPieces.addAll(Bishop.initialBishops());
        initialPieces.addAll(Pawn.initialPawns());
        return initialPieces;
    }
}
