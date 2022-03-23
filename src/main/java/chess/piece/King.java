package chess.piece;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class King extends Piece {

    private static final List<Integer> X = List.of(0, 0, -1, -1, -1, 1, 1, 1);
    private static final List<Integer> Y = List.of(1, -1, 1, 0, -1, 1, 0, -1);

    public King(Color color) {
        super(Type.KING, color);
    }

    @Override
    boolean isMovable(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        for (int index = 0; index < X.size(); index++) {
            if (source.getLeft() + X.get(index) == target.getLeft()
                    && source.getRight() + Y.get(index) == target.getRight()) {
                return true;
            }
        }
        return false;
    }
}
