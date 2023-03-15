package chess.domain;

import chess.domain.board.Board;

public class ChessGame {

    public Board createBoard() {
        return Board.create();
    }
}
