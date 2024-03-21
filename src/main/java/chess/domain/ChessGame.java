package chess.domain;

public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void start() {
        //
    }

    public void end() {
        //
    }

    public void move(Position source, Position target) {
        board.movePieceAndRenewBoard(source, target);
    }

    public Board getBoard() {
        return board;
    }
}
