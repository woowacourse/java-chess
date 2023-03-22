package chess.domain;

import chess.domain.board.Board;
import chess.domain.square.Square;
import chess.domain.state.State;
import chess.domain.state.WaitingStart;

public class ChessGame {
    private State state;

    public ChessGame() {
        this.state = new WaitingStart();
    }

    public void start() {
        this.state = state.start();
    }

    public void move(final Square sourceSquare, final Square targetSquare) {
        state.move(sourceSquare, targetSquare);
    }

    public void end() {
        this.state = state.end();
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public boolean isRunning() {
        return state.isRunning();
    }
}
