package chess.domain.state;

import chess.domain.position.Position;
import chess.domain.position.Result;
import chess.domain.board.Color;
import chess.domain.board.Piece;
import java.util.Map;

abstract class Finished implements State {

    @Override
    public State movePiece(Position src, Position dest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<Color, Double> getScore() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Result getResult() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
