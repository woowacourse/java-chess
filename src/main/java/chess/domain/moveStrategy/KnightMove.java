package chess.domain.moveStrategy;

import chess.domain.piece.Color;
import chess.domain.location.Vector;

import java.util.Arrays;
import java.util.List;

public class KnightMove extends SingleMove {

    public KnightMove(Color color) {
        super(color);
    }

    @Override
    public List<Vector> directions() {
        return Arrays.asList(
                new Vector(2, 1),
                new Vector(2, -1),
                new Vector(-2, 1),
                new Vector(-2, -1),
                new Vector(1, 2),
                new Vector(1, -2),
                new Vector(-1, 2),
                new Vector(-1, -2)
        );
    }
}
