package chess.domain;

import chess.domain.board.Board;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.State;

public class ChessGame {
    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public void start() {
        this.state = this.state.start();
    }

    public Board getBoard() {
        return this.state.getBoard();
    }

    public boolean isRunning() {
        return this.state.isRunning();
    }

    public void end() {
        this.state = this.state.end();
    }
}
