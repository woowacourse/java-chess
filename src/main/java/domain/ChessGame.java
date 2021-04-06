package domain;

import domain.piece.position.Position;
import domain.state.State;

public class ChessGame {
    private State state;

    public ChessGame(State state) {
        this.state = state;
    }

    public void start() {
        state = state.run();
    }

    public void move(Position start, Position end) {
        state = state.move(start, end);
    }

    public void finish() {
        state = state.finish();
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public double blackScore() {
        return state.blackScore().getValue();
    }

    public double whiteScore() {
        return state.whiteScore().getValue();
    }

    public boolean getTurn() {
        return state.getTurn();
    }

    public boolean isEnd() {
        return state.isEnd();
    }
}
