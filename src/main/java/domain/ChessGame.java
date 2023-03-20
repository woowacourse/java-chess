package domain;

import domain.type.Turn;
import java.util.HashMap;

public class ChessGame {

    private Board board;
    private Turn turn;

    public ChessGame() {
        this.turn = Turn.WHITE;
    }

    public void initialize() {
        this.board = new Board(new PathValidator(), new HashMap<>());
        board.initialize();
    }

    public void move(final Location start, final Location end) {
        if (turn.equals(Turn.WHITE)) {
            board.moveWhite(start, end);
            turn = Turn.BLACK;
            return;
        }
        board.moveBlack(start, end);
        turn = Turn.WHITE;
    }

    public Board getBoard() {
        return board;
    }
}
