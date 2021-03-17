package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public class CurrentPieces {

    private List<Piece> currentPieces;

    private CurrentPieces(List<Piece> currentPieces) {
        this.currentPieces = currentPieces;
    }

    public static CurrentPieces generate() {
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(King.generate());
        pieces.addAll(Knight.generate());
        pieces.addAll(Queen.generate());
        pieces.addAll(Rook.generate());
        pieces.addAll(Bishop.generate());
        pieces.addAll(Pawn.generate());
        return new CurrentPieces(pieces);
    }

    public List<Piece> getCurrentPieces() {
        return currentPieces;
    }

    public Piece findByPosition(Position position) {
        return currentPieces.stream()
                .filter(piece -> position.equals(piece.getPosition()))
                .findFirst()
                .orElse(Empty.EMPTY);
    }
}
