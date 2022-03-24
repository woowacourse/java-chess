package chess.controller;

import chess.model.Board;
import chess.model.Square;

public class ChessService {
    private Board board;

    public Board initBoard() {
        board = new Board();
        return board;
    }

    public Board move(String from, String to) {
        board.move(Square.of(from), Square.of(to));
        return board;
    }
}
