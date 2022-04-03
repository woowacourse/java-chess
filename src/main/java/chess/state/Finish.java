package chess.state;

import chess.chessgame.Chessboard;
import chess.chessgame.MovingPosition;
import chess.chessgame.Turn;
import chess.piece.Color;

public class Finish implements State {

    private final Chessboard chessboard;

    public Finish(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException(UNSUPPORTED_STATE);
    }

    @Override
    public State move(MovingPosition movingPosition, Turn turn) {
        throw new UnsupportedOperationException(UNSUPPORTED_STATE);
    }

    @Override
    public Chessboard getChessboard() {
        return chessboard;
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException(UNSUPPORTED_STATE);
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double computeScore(Color color, double minusScoreOfSameColumnPawn) {
        return chessboard.computeScore(color, minusScoreOfSameColumnPawn);
    }

}
