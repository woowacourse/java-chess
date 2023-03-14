package chess.domain;

import java.util.Map;

public class ChessBoard {
    private final Map<Square, Piece> pieces;

    public ChessBoard(Map<Square, Piece> pieces) {
        this.pieces = pieces;
    }

    public Map<Square, Piece> getPieces() {
        return Map.copyOf(pieces);
    }
}
