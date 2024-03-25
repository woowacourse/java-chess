package domain.game.state;

import domain.board.ChessBoard;
import domain.position.Position;

public abstract class GameState {
    private final ChessBoard board;

    protected GameState(ChessBoard board) {
        this.board = board;
    }

    public abstract GameState start();

    public abstract GameState move(Position source, Position target);

    public abstract GameState end();

    public boolean isRunning() {
        return true;
    }

    public final ChessBoard chessBoard() {
        return this.board;
    }
}
