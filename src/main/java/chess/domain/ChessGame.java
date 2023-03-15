package chess.domain;

public class ChessGame {

    private boolean playable;
    private final Board board;

    public ChessGame(final Board board) {
        this.playable = false;
        this.board = board;
    }

    public void start() {
        this.playable = true;
    }

    public void end() {
        this.playable = false;
    }

    public boolean isPlayable() {
        return playable;
    }

    public Board getBoard() {
        return board;
    }
}
