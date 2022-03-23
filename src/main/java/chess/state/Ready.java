package chess.state;

import chess.Chessboard;
import org.apache.commons.lang3.tuple.Pair;

public class Ready implements State {

    Chessboard chessboard;

    public Ready() {
        chessboard = Chessboard.emptyChessboard();
    }

    @Override
    public State start() {
        return new Play();
    }

    @Override
    public State move(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        throw new UnsupportedOperationException();
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
