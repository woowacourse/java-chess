package refactorChess.domain.game;

import refactorChess.domain.board.ChessBoard;
import refactorChess.domain.board.Position;
import refactorChess.domain.state.State;

public class ChessGame {

    private State state;

    public ChessGame(State state) {
        this.state = state;
    }

    public void start() {
        state = state.start();
    }

    public void move(Position from, Position to) {
        state = state.move(from, to);
    }

    public void end() {
        state = state.end();
    }

    public Status status() {
        return state.getStatus();
    }

    public ChessBoard board() {
        return state.getChessBoard();
    }

    public boolean isFinished() {
        return state.isFinished();
    }
}
