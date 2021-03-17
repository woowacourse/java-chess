package chess.domain;

import chess.domain.board.Board;

public class Game {

    private final Board board;

    private Game() {
        this.board = new Board();
    }

    public static Game init() {
        return new Game();
    }

    public Board getBoard() {
        return board;
    }
}
