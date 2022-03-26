package chess.state;

import chess.Chessboard;
import chess.Turn;
import chess.piece.Color;
import org.apache.commons.lang3.tuple.Pair;

public class Ready implements State {

    private final Chessboard chessboard;
    private final Turn turn;

    public Ready() {
        chessboard = Chessboard.emptyChessboard();
        turn = new Turn();
    }

    @Override
    public State start() {
        return new Play(turn);
    }

    @Override
    public State move(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public State end() {
        return new Finish(chessboard);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double computeScore(Color color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Chessboard getChessboard() {
        return chessboard;
    }
}
