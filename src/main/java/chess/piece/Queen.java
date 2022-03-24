package chess.piece;

import org.apache.commons.lang3.tuple.Pair;

public class Queen extends Piece {

    public Queen(Color color) {
        super(Type.QUEEN, color);
    }

    @Override
    public boolean isMovable(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        return Math.abs(source.getRight() - target.getRight()) == Math.abs(source.getLeft() - target.getLeft())
                || isSameLine(source, target);
    }

    private boolean isSameLine(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        return (source.getRight() == target.getRight() || source.getLeft() == target.getLeft());
    }
}
