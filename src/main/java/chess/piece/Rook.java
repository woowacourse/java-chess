package chess.piece;

import org.apache.commons.lang3.tuple.Pair;

public class Rook extends Piece {

    public Rook(Color color) {
        super(Type.ROOK, color);
    }

    @Override
    boolean isMovable(Pair<Integer,Integer> source, Pair<Integer,Integer> target) {
        return false;
    }
}
