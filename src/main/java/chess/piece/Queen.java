package chess.piece;

import chess.chessgame.Position;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color) {
        super(Type.QUEEN, color);
    }

    @Override
    public boolean isMovable(Position position) {
        return position.isCross() || position.isLinear();
    }

    @Override
    public List<Pair<Integer, Integer>> computeMiddlePosition(Position position) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        list.addAll(position.computeLinearMiddle());
        list.addAll(position.computeCrossMiddle());

        return list;
    }

}
