package chess.board;

import chess.Color;
import chess.Position;
import chess.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public boolean isSameColorPieceExists(Position goal, Color color) {
        // TODO
        return false;
    }

    public boolean isPieceExists(Position position) {
        // TODO
        return false;
    }
}
