package chess.state;

import chess.chessgame.Chessboard;
import chess.chessgame.Position;
import chess.chessgame.Turn;
import chess.piece.Color;

public class Ready implements State {

    private final Chessboard chessboard;

    public Ready() {
        chessboard = Chessboard.emptyChessboard();
    }

    @Override
    public State start() {
        return new Play();
    }

    @Override
    public State move(Position position, Turn turn) {
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
    public double computeScore(Color color) {
        throw new UnsupportedOperationException();
    }
}
