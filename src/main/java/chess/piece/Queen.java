package chess.piece;

import org.apache.commons.lang3.tuple.Pair;

public class Queen extends Piece {

    public Queen(Color color) {
        super(Type.QUEEN, color);
    }

    @Override
    boolean isMovable(Pair<Integer,Integer> source, Pair<Integer,Integer> target) {
        return false;
    }
}
