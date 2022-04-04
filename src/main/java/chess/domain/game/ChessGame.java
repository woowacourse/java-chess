package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.game.state.Start;
import chess.domain.game.state.State;

public class ChessGame {

    private State state;

    public ChessGame() {
        state = new Start();
    }

    public void start() {
        state = state.start();
    }

    public void move(Coordinate from, Coordinate to) {
        state = state.move(from, to);
    }

    public void end() {
        state = state.end();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public StatusCalculator status() {
        return new StatusCalculator(getBoard().getValue());
    }

    public Board getBoard() {
        return state.getBoard();
    }
}
