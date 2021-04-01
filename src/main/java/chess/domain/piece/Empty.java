package chess.domain.piece;

import chess.domain.game.Board;
import chess.domain.location.Position;

import java.util.List;

public class Empty extends Basis {
    public Empty() {
        super(".");
    }

    @Override
    public boolean isSameColor(Color color) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public double score() {
        return 0;
    }

    @Override
    public List<Position> movablePositions(Position from, Board board) {
        throw new UnsupportedOperationException();
    }

}
