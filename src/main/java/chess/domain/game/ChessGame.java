package chess.domain.game;

public class ChessGame {

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void progress(Position source, Position target) {
        board.move(source, target);
    }
}
