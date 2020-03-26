package chess.domain.board;


import chess.domain.Piece.state.PieceState;
import chess.domain.Position;

import java.util.LinkedHashMap;

public class Board {
    private static final int SIZE = 64;
    private static final String INVALID_SIZE = "옳바르지 않은 갯수입니다.";

    private final LinkedHashMap<Position, PieceState> board;

    Board(LinkedHashMap<Position, PieceState> board) {
        if (board.size() != SIZE) {
            throw new IllegalStateException(INVALID_SIZE);
        }
        this.board = board;
    }

    int size() {
        return board.size();
    }

    PieceState get(Position position) {
        return board.get(position);
    }

    public LinkedHashMap<Position, PieceState> getBoard() {
        return board;
    }
}
