package chess.domain.game;

import chess.domain.game.state.State;
import chess.domain.piece.Position;

public class ChessGame {
    private final int id;
    private State state;

    public ChessGame(int id, State state) {
        this.id = id;
        this.state = state;
    }

    public void start() {
        state = state.start();
    }

    public void move(Position source, Position target) {
        state = state.move(source, target);
    }

    public void end() {
        state = state.end();
    }

    public Board board() {
        return state.board();
    }

    public Turn turn() {
        return state.turn();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public Status status() {
        return state.status();
    }

    public int getId() {
        return id;
    }

    public State getState() {
        return state;
    }
}
