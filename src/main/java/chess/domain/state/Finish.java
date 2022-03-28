package chess.domain.state;

import chess.domain.board.Chessboard;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Finish implements State {

    private final Chessboard chessboard;

    public Finish(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State move(Position source, Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return true;
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
