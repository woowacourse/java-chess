package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.ScoreResult;

abstract class Finished implements State {

    @Override
    public State movePiece(Position src, Position dest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Board getBoard() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ScoreResult getScore() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
