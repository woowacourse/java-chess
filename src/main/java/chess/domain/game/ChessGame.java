package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.state.State;
import chess.domain.state.Turn;

public class ChessGame {

    private Long id;
    private State state;

    public ChessGame(Long id, State state) {
        this.id = id;
        this.state = state;
    }

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
        return state.status();
    }

    public ChessBoard board() {
        return state.chessBoard();
    }

    public Turn turn() {
        return state.turn();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public Long getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return "ChessGame{" +
                "id=" + id +
                ", state=" + state +
                '}';
    }
}
