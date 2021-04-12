package chess.domain.piece;

import chess.domain.location.Vector;
import chess.domain.moveStrategy.MultipleMove;

import java.util.Arrays;
import java.util.List;

public class Queen extends Division {
    public static final int QUEEN_SCORE = 9;
    public static List<Vector> DIRECTIONS = Arrays.asList(
            new Vector(1, 0),
            new Vector(0, 1),
            new Vector(-1, 0),
            new Vector(0, -1),
            new Vector(1, 1),
            new Vector(1, -1),
            new Vector(-1, 1),
            new Vector(-1, -1)
    );

    public Queen(Color color) {
        super(color, QUEEN_SCORE, Unicode.QUEEN.of(color), new MultipleMove(color, DIRECTIONS));
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
