package chess.web;

import chess.domain.ChessScore;
import chess.domain.board.Board;
import chess.domain.state.State;
import chess.domain.command.Command;

public class MyState {

    private State state;

    private MyState() {
        this.state = State.create();
    }

    public static MyState generate() {
        return new MyState();
    }

    public void run(Command command) {
        this.state = state.proceed(command);
    }

    public boolean isFinished() {
        return this.state.isFinished();
    }

    public ChessScore generateScore() {
        return this.state.generateScore();
    }

    public boolean isRunning() {
        return this.state.isRunning();
    }

    public boolean isWhite() {
        return this.state.isWhite();
    }

    public Board getBoard() {
        return this.state.getBoard();
    }
}
