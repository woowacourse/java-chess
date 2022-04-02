package chess.domain;

import chess.domain.board.Chessboard;
import chess.domain.piece.Color;
import chess.domain.position.Position;
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

    public void move(Position source, Position target) {
        state = state.move(source, target);
    }

    public void end() {
        state = state.end();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public double computeScore(Color color) {
        return state.computeScore(color);
    }

    public Chessboard getChessBoard() {
        return state.getChessboard();
    }
}
