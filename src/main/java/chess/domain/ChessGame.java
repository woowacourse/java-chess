package chess.domain;

import chess.domain.board.Chessboard;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;

public class ChessGame {

    private static final int ROW = 1;
    private static final int COLUMN = 0;

    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public void start() {
        state = state.start();
    }

    public void move(String[] source, String[] target) {
        state = state.move(Position.of(source[ROW], source[COLUMN]),
                Position.of(target[ROW], target[COLUMN]));
    }

    public void end() {
        state = state.end();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public double computeScore(Color color){
        return state.computeScore(color);
    }

    public Chessboard getChessBoard() {
        return state.getChessboard();
    }
}
