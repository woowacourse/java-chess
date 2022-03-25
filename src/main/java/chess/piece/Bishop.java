package chess.piece;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

import static chess.utils.MovingBetweenPositions.computeLeftDownRightUp;
import static chess.utils.MovingBetweenPositions.computeLeftUpRightDown;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(Type.BISHOP, color);
    }

    @Override
    public List<Pair<Integer, Integer>> computeBetweenTwoPosition(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if ((source.getLeft() - target.getLeft()) == (-1) * (source.getRight() - target.getRight())) {
            return computeLeftDownRightUp(source, target);
        }
        if ((source.getLeft() - target.getLeft()) == (source.getRight() - target.getRight())) {
            return computeLeftUpRightDown(source, target);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isMovable(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        return Math.abs(source.getRight() - target.getRight()) == Math.abs(source.getLeft() - target.getLeft());
    }
}
