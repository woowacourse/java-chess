package chess.piece;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

import static chess.utils.MovingBetweenPositions.*;
import static chess.utils.MovingBetweenPositions.computeLeftUpRightDown;

public class Queen extends Piece {

    public Queen(Color color) {
        super(Type.QUEEN, color);
    }

    @Override
    public boolean isMovable(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        return Math.abs(source.getRight() - target.getRight()) == Math.abs(source.getLeft() - target.getLeft())
                || isSameLine(source, target);
    }

    @Override
    public List<Pair<Integer, Integer>> computeBetweenTwoPosition(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        if (source.getLeft() == target.getLeft()) {
            result.addAll(computeBetweenPositionsOfRow(source, target));
        }
        if (source.getRight() == target.getRight()) {
            result.addAll(computeBetweenPositionsOfColumn(source, target));
        }

        if ((source.getLeft() - target.getLeft()) == (-1) * (source.getRight() - target.getRight())) {
            result.addAll(computeLeftDownRightUp(source, target));
        }
        if ((source.getLeft() - target.getLeft()) == (source.getRight() - target.getRight())) {
            result.addAll(computeLeftUpRightDown(source, target));
        }

        return result;
    }

    private boolean isSameLine(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        return (source.getRight() == target.getRight() || source.getLeft() == target.getLeft());
    }
}
