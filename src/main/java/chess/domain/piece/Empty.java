package chess.domain.piece;

import chess.domain.location.Position;

import java.util.List;
import java.util.Map;

public class Empty extends Basis {
    public Empty() {
        super(' ');
    }

    @Override
    public boolean isSame(Color color) {
        return false;
    }

    @Override
    public boolean isOpposite(Color color) {
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
    public List<Position> movablePositions(Position from, Map<Position, Piece> pieceByPosition) {
        throw new UnsupportedOperationException();
    }

}
