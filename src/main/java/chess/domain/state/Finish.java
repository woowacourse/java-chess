package chess.domain.state;

import chess.Score;
import chess.domain.board.Chessboard;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Finish implements State {

    private static final String EXCEPTION_MOVE_IMPOSSIBLE = "Finish 상태에서 움직일 수 없습니다.";

    private final Chessboard chessboard;

    public Finish(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    @Override
    public State start() {
        return new Ready().start();
    }

    @Override
    public State move(Position source, Position target) {
        throw new UnsupportedOperationException(EXCEPTION_MOVE_IMPOSSIBLE);
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
    public Score computeScore(Color color) {
        return chessboard.computeScore(color);
    }

    @Override
    public Chessboard getChessboard() {
        return chessboard;
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
