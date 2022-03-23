package chess.domain;

public class Board {
    private final Grid[][] board;

    public Board(Grid[][] board) {
        this.board = board;
    }

    public Grid[][] getBoard() {
        return board;
    }
}
