package chess.domain.piece;

import chess.domain.location.Vector;
import chess.domain.moveStrategy.MultipleMove;

import java.util.Arrays;
import java.util.List;

public class Rook extends Division {
    public static final int ROOK_SCORE = 5;
    public static List<Vector> DIRECTIONS = Arrays.asList(
            new Vector(1, 0),
            new Vector(0, 1),
            new Vector(-1, 0),
            new Vector(0, -1));

    public Rook(Color color) {
        super(color, Unicode.ROOK.of(color), new MultipleMove(color, DIRECTIONS));
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public double score() {
        return ROOK_SCORE;
    }
}
