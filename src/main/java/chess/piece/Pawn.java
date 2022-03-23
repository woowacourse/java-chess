package chess.piece;

import org.apache.commons.lang3.tuple.Pair;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(Type.PAWN, color);
    }

    @Override
    boolean isMovable(Pair<Integer,Integer> source, Pair<Integer,Integer> target) {
        return false;
    }
}