package domain;

public final class ChessGame {

    private Board board;
    private boolean isWhite = true;

    public void initialize() {
        this.board = new Board(new PathValidator());
        board.initialize();
        isWhite = true;
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
