package chess.domain.state;

import chess.domain.Turn;
import chess.domain.board.Chessboard;
import chess.domain.piece.Color;
import chess.domain.position.Position;

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
    public State move(Position source, Position target) {
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
