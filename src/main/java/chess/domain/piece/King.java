package chess.domain.piece;

import chess.domain.location.Vector;
import chess.domain.moveStrategy.SingleMove;

import java.util.Arrays;
import java.util.List;

public class King extends Division {
    public static final int KING_SCORE = 0;

    public static List<Vector> DIRECTIONS = Arrays.asList(
            new Vector(1, 1),
            new Vector(1, 0),
            new Vector(1, -1),
            new Vector(-1, 1),
            new Vector(-1, 0),
            new Vector(-1, -1),
            new Vector(0, 1),
            new Vector(0, -1)
    );

    public King(Color color) {
        super(color, KING_SCORE, Unicode.KING.of(color), new SingleMove(color, DIRECTIONS));
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
