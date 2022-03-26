package chess.chessgame;

import chess.piece.Color;
import chess.state.Finish;
import chess.state.Ready;
import chess.state.State;

public class ChessGame {

    private State state;
    private Turn turn;

    public ChessGame() {
        this.state = new Ready();
        this.turn = new Turn();
    }

    public void start() {
        state = state.start();
    }

    public void move(Position position) {
        state = state.move(position, turn);
    }

    public void end() {
        state = new Finish(getChessBoard());
    }

    public Chessboard getChessBoard() {
        return state.getChessboard();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public double computeScore(Color color) {
        return state.computeScore(color);
    }

}
