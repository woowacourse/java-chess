package chess.state;

import chess.Chessboard;
import chess.Turn;
import chess.piece.Color;
import org.apache.commons.lang3.tuple.Pair;

public class Finish implements State {

    private final Chessboard chessboard;

    public Finish(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State move(Pair<Integer, Integer> source, Pair<Integer, Integer> target, Turn turn) {
        throw new UnsupportedOperationException();
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Chessboard getChessboard() {
        return chessboard;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double computeScore(Color color) {
        return chessboard.computeScore(color);
    }
}
