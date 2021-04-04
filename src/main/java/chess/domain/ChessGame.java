package chess.domain;

import chess.domain.board.Board;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.State;
import chess.domain.position.Position;

public class ChessGame {

    private State state;

    public ChessGame(Board board) {
        this.state = new Ready(board);
    }

    public void start() {
        state = state.start();
    }

    public void move(Position from, Position to) {
        state = state.move(from, to);
    }

    public void status() {
        state = state.status();
    }

    public void end() {
        state = state.finished();
    }

    public Score score() {
        return state.score();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean isGameSet() {
        return state.isGameSet();
    }

    public State state() {
        return state;
    }

    public Board board() {
        return state.board();
    }

    public Side side() {
        return state.side();
    }
}
