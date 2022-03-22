package chess.state;

import chess.Chessboard;
import org.apache.commons.lang3.tuple.Pair;

public class Finish implements State {

    Chessboard chessboard;

    public Finish(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State move(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Chessboard getChessboard() {
        return chessboard;
    }
}
