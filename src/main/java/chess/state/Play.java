package chess.state;

import chess.Chessboard;
import chess.Turn;
import chess.piece.Color;

import org.apache.commons.lang3.tuple.Pair;

public class Play implements State {

    private final Chessboard chessboard;
    private final Turn turn;

    public Play(Turn turn) {
        this.turn = turn;
        this.chessboard = Chessboard.initializedChessboard();
    }

    public Play(Turn turn, Chessboard chessboard) {
        this.turn = turn;
        this.chessboard = chessboard;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State move(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        chessboard.movePiece(source, target, turn);

        if (chessboard.isKing(target)) {
            return new Finish(chessboard);
        }
        turn.nextTurn();
        return new Play(turn, chessboard);
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
        return chessboard.computeScore(color);
    }

    @Override
    public Chessboard getChessboard() {
        return chessboard;
    }
}
