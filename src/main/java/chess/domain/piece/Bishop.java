package chess.domain.piece;

import chess.domain.location.Vector;
import chess.domain.moveStrategy.MultipleMove;

import java.util.Arrays;
import java.util.List;

public class Bishop extends Division {
    public static final int BISHOP_SCORE = 3;
    private static final List<Vector> DIRECTIONS = Arrays.asList(
            new Vector(1, 1),
            new Vector(1, -1),
            new Vector(-1, 1),
            new Vector(-1, -1)
    );

    public Bishop(Color color) {
        super(color, BISHOP_SCORE, Unicode.BISHOP.of(color), new MultipleMove(color, DIRECTIONS));
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

}
