package chess.board;

import chess.piece.ColoredPiece;
import java.util.Map;

public class Board {

    private final Map<Position, ColoredPiece> pieces;

    public Board(Map<Position, ColoredPiece> pieces) {
        this.pieces = pieces;
    }

    public Map<Position, ColoredPiece> getPieces() {
        return pieces;
    }
}
