package chess.domain;

import chess.domain.state.State;
import chess.domain.state.StateType;

public class ChessGame {

    private State state;

    public ChessGame(State state) {
        this.state = state;
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

    public StateType getStateType() {
        return state.getStateType();
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
