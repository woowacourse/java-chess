package chess.state;

import chess.chessgame.Chessboard;
import chess.chessgame.MovingPosition;
import chess.chessgame.Turn;
import chess.piece.Color;

import java.util.ArrayList;

public class Ready implements State {

    private final Chessboard chessboard;

    public Ready() {
        chessboard = new Chessboard(() -> new ArrayList<>());
    }

    @Override
    public State start() {
        return new Play();
    }

    @Override
    public State move(MovingPosition movingPosition, Turn turn) {
        throw new UnsupportedOperationException();
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
    public double computeScore(Color color, double minusScoreOfSameColumnPawn) {
        throw new UnsupportedOperationException();
    }
}
