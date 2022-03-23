package chess.state;

import chess.Chessboard;
import org.apache.commons.lang3.tuple.Pair;


public class Play implements State {

    Chessboard chessboard;

    public Play() {
        this.chessboard = Chessboard.initializedChessboard();
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State move(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        throw new IllegalStateException();
    }

    @Override
    public Chessboard getChessboard() {
        return chessboard;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
