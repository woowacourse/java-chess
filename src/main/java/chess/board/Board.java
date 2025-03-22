package chess.board;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.piece.Piece;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public void move(Color color, Position start, Position goal) {
        Piece piece = board.get(start);
        if (piece == null) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }
        if (piece.isDifferentColor(color)) {
            throw new IllegalArgumentException("다른 팀의 기물을 움직일 수 없습니다.");
        }
        piece.validateMovable(this, start, goal);
        board.put(goal, piece);
        board.remove(start);
    }

    public boolean isSameColorPieceExists(Position goal, Color color) {
        Piece piece = board.get(goal);
        if (piece == null) {
            return false;
        }
        return piece.isSameColor(color);
    }

    public boolean isDifferentColorPieceNotExists(Position start, Position goal) {
        Piece startPiece = board.get(start);
        Piece goalPiece = board.get(goal);
        if (startPiece == null || goalPiece == null) {
            return true;
        }
        return startPiece.isSameColor(goalPiece);
    }

    public boolean isPieceExists(Position position) {
        Piece piece = board.get(position);
        return piece != null;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
