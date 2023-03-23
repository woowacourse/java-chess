package chess.domain;

import chess.domain.board.Board;
import chess.domain.square.Color;
import chess.domain.square.Side;
import chess.domain.square.Square;
import chess.domain.state.State;
import chess.domain.state.WaitingStart;

import java.util.Map;

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

    public Map<Side, Double> status() {
        return Map.of(
                Side.from(Color.WHITE), state.calculateScore(Side.from(Color.WHITE)),
                Side.from(Color.BLACK), state.calculateScore(Side.from(Color.BLACK))
        );
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
