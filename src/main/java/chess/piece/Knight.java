package chess.piece;

import org.apache.commons.lang3.tuple.Pair;

public class Knight extends Piece {

    public Knight(Color color) {
        super(Type.KNIGHT, color);
    }

    @Override
    boolean isMovable(Pair<Integer,Integer> source, Pair<Integer,Integer> target) {
        return false;
    }
}