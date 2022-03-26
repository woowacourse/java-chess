package chess.state;

import chess.chessgame.Chessboard;
import chess.chessgame.Position;
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
        throw new UnsupportedOperationException();
    }

    @Override
    public State move(Position position, Turn turn) {
        boolean isKing = chessboard.move(position, turn);
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
    public double computeScore(Color color, double minusScoreOfSameColumnPawn) {
        return chessboard.computeScore(color, minusScoreOfSameColumnPawn);
    }
}
