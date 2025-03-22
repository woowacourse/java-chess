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

    public void move(Position start, Position goal) {
        Piece piece = board.get(start);
        piece.validateMovable(this, start, goal);
    }

    public boolean isSameColorPieceExists(Position goal, Color color) {
        Piece piece = board.get(goal);
        if (piece == null) {
            return false;
        }
        return piece.isSameColor(color);
    }

    public boolean isPieceExists(Position position) {
        Piece piece = board.get(position);
        return piece != null;
    }
}
