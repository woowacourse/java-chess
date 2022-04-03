package chess.state;

import chess.chessgame.Chessboard;
import chess.chessgame.MovingPosition;
import chess.chessgame.Turn;
import chess.piece.Color;
import chess.utils.InitializedChessboardGenerator;


public class Play implements State {

    private final Chessboard chessboard;

    public Play() {
        this.chessboard = new Chessboard(new InitializedChessboardGenerator());
    }

    public Play(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException(UNSUPPORTED_STATE);
    }

    @Override
    public State move(MovingPosition movingPosition, Turn turn) {
        chessboard.move(movingPosition, turn);

        if (chessboard.isOver()) {
            return new Finish(chessboard);
        }

        turn.nextTurn();
        return new Play(chessboard);
    }

    @Override
    public Chessboard getChessboard() {
        return chessboard;
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
    public boolean isPlaying() {
        return true;
    }

    @Override
    public double computeScore(Color color, double minusScoreOfSameColumnPawn) {
        return chessboard.computeScore(color, minusScoreOfSameColumnPawn);
    }

}
