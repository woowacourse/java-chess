package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;

public class CatchPieces {

    private final List<Piece> blackPieces;
    private final List<Piece> whitePieces;

    public CatchPieces() {
        this.blackPieces = new ArrayList<>();
        this.whitePieces = new ArrayList<>();
    }

    public void addPiece(final Piece piece) {
        if (piece.isBlank()) {
            return;
        }
        if (piece.getColor() == Color.BLACK) {
            blackPieces.add(piece);
        }
        whitePieces.add(piece);
    }

    public boolean isKingCatch(final Color color) {
        if (color == Color.BLACK) {
            return blackPieces.stream().anyMatch(Piece::isKing);
        }
        return whitePieces.stream().anyMatch(Piece::isKing);
    }
}
