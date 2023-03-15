package domain;

public class ChessGame {

    private final Board board;

    public ChessGame() {
        this.board = new Board(new PathValidator());
    }

    public void move(final Location start, final Location end) {
        board.move(start, end);
    }

    public Board getBoard() {
        return board;
    }
}
