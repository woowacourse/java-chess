package chess.piece;

import chess.chessgame.Position;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(Type.BISHOP, color);
    }

    @Override
    public boolean isMovable(Position position) {
        return position.isCross();
    }

    @Override
    public List<Pair<Integer, Integer>> computeMiddlePosition(Position position) {
        return position.computeCrossMiddle();
    }
}
