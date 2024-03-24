package chess.domain;

import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board() {
        this.board = new HashMap<>();
    }

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public void putPiece(Position position, Piece piece) {
        board.put(position, piece);
    }

    public void movePiece(Position from, Position to) {
        Piece piece = board.get(from);
        board.put(to, piece);
        board.remove(from);
    }

    public boolean hasPiece(Position position) {
        return board.containsKey(position);
    }

    public Piece findPieceByPosition(Position position) {
        if (hasPiece(position)) {
            return board.get(position);
        }
        throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
    }

    public boolean isEmptySpace(Position position) {
        return !hasPiece(position);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
