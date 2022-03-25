package chess.piece;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

import static chess.utils.MovingBetweenPositions.computeBetweenPositionsOfColumn;
import static chess.utils.MovingBetweenPositions.computeBetweenPositionsOfRow;

public class Rook extends Piece {

    public Rook(Color color) {
        super(Type.ROOK, color);
    }

    @Override
    public List<Pair<Integer, Integer>> computeBetweenTwoPosition(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if (source.getLeft() == target.getLeft()) {
            return computeBetweenPositionsOfRow(source, target);
        }
        if (source.getRight() == target.getRight()) {
            return computeBetweenPositionsOfColumn(source, target);
        }

        return new ArrayList<>();
    }

    @Override
    public boolean isMovable(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        return source.getRight() == target.getRight() || source.getLeft() == target.getLeft();
    }
}


