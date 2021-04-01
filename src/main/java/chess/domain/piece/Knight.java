package chess.domain.piece;

import chess.domain.location.Vector;
import chess.domain.moveStrategy.SingleMove;

import java.util.Arrays;
import java.util.List;

public class Knight extends Division {
    public static final double KNIGHT_SCORE = 2.5;

    public static List<Vector> DIRECTIONS = Arrays.asList(
            new Vector(2, 1),
            new Vector(2, -1),
            new Vector(-2, 1),
            new Vector(-2, -1),
            new Vector(1, 2),
            new Vector(1, -2),
            new Vector(-1, 2),
            new Vector(-1, -2)
    );

    public Knight(Color color) {
        super(color, "n", new SingleMove(color,DIRECTIONS), new SingleMove(color, DIRECTIONS));
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
        return KNIGHT_SCORE;
    }
}
