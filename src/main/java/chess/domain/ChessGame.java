package chess.domain;

import chess.domain.board.Board;

public class ChessGame {
    public Board initializeBoard() {
        return new Board();
    }
}
