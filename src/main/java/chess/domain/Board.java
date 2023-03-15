package chess.domain;

import chess.domain.piece.Piece;
import java.util.Map;

public class Board {
    private final Map<Square, Piece> pieces;

    public Board() {
        this.pieces = InitPieces.initPieces();
    }

    public Map<Square, Piece> getPieces() {
        return pieces;
    }
}
