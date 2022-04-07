package chess.domain;

import chess.domain.state.State;

public class ChessGame {

    private State state;

    public ChessGame(State state) {
        this.state = state;
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

    public boolean isStarted() {
        return state.isStarted();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public State getState() {
        return state;
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
