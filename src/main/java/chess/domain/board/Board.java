package chess.domain.board;


import chess.domain.Piece.Piece;
import chess.domain.position.Position;

import java.util.LinkedHashMap;

public class Board {
    private static final int SIZE = 64;
    private static final String INVALID_SIZE = "옳바르지 않은 갯수입니다.";

    private final LinkedHashMap<Position, Piece> board;

    Board(LinkedHashMap<Position, Piece> board) {
        if (board.size() != SIZE) {
            throw new IllegalStateException(INVALID_SIZE);
        }
        this.board = board;
    }

    int size() {
        return board.size();
    }

    Piece get(Position position) {
        return board.get(position);
    }

    public LinkedHashMap<Position, Piece> getBoard() {
        return board;
    }
}
