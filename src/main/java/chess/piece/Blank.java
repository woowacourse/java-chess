package chess.piece;

import org.apache.commons.lang3.tuple.Pair;

public class Blank extends Piece {

    public Blank() {
        super(Type.BLANK, Color.NONE);
    }

    @Override
    boolean isMovable(Pair<Integer,Integer> source, Pair<Integer,Integer> target) {
        return false;
    }
}
