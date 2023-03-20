package domain;

import java.util.HashMap;

public class ChessGame {

    private Board board;
    private boolean isWhite;

    public ChessGame() {
        this.isWhite = true;
    }

    public void initialize() {
        this.board = new Board(new PathValidator(), new HashMap<>());
        board.initialize();
    }

    public void move(final Location start, final Location end) {
        if (isWhite) {
            board.moveWhite(start, end);
            isWhite = false;
            return;
        }
        board.moveBlack(start, end);
        isWhite = true;
    }

    public Board getBoard() {
        return board;
    }
}
