package domain;

public class ChessGame {

    private final Board board;
    private boolean isWhite;

    public ChessGame() {
        this.board = new Board(new PathValidator());
        this.isWhite = true;
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
