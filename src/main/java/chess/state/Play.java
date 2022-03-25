package chess.state;

import chess.Chessboard;
import chess.Turn;
import chess.piece.Color;
import org.apache.commons.lang3.tuple.Pair;


public class Play implements State {

    private final Chessboard chessboard;

    public Play() {
        this.chessboard = Chessboard.initializedChessboard();
    }

    public Play(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State move(Pair<Integer, Integer> source, Pair<Integer, Integer> target, Turn turn) {
        boolean isKing = chessboard.movePiece(source, target, turn);
        if (isKing) {
            return new Finish(chessboard);
        }
        turn.nextTurn();
        return new Play(chessboard);
    }

    @Override
    public State end() {
        return new Finish(chessboard);
    }

    @Override
    public Chessboard getChessboard() {
        return chessboard;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double computeScore(Color color) {
        return chessboard.computeScore(color);
    }
}
