package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public class PieceFactory {
    public PieceFactory() {
    }

    public List<Piece> initialPieces() {
        List<Piece> initialPieces = new ArrayList<>();
        initialPieces.addAll(King.generate());
        initialPieces.addAll(Knight.generate());
        initialPieces.addAll(Queen.generate());
        initialPieces.addAll(Rook.generate());
        initialPieces.addAll(Bishop.generate());
        initialPieces.addAll(Pawn.generate());
        return initialPieces;
    }
}
