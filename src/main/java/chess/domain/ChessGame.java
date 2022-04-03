package chess.domain;

import chess.domain.state.Ready;
import chess.domain.state.State;

public class ChessGame {

    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public void start() {
        state = state.start();
    }

    public void end() {
        state = state.end();
    }

    public void move(String source, String target) {
        state = state.move(source, target);
    }

    public void reset() {
        state = new Ready();
    }

    public boolean isStarted() {
        return state.isStarted();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public double score(Color color) {
        ChessBoard chessBoard = state.chessBoard();
        return chessBoard.calculateScore(color);
    }

    public Result result() {
        return state.winner();
    }

    public Board board() {
        ChessBoard chessBoard = state.chessBoard();
        return chessBoard.getBoard();
    }
}
