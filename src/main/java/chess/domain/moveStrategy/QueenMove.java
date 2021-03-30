package chess.domain.moveStrategy;

import chess.domain.piece.Color;
import chess.domain.piece.Vector;

import java.util.Arrays;
import java.util.List;

public class QueenMove extends MultipleMove {
    public QueenMove(Color color) {
        super(color);
    }

    @Override
    public List<Vector> directions() {
        return Arrays.asList(
                new Vector(1, 0),
                new Vector(0, 1),
                new Vector(-1, 0),
                new Vector(0, -1),
                new Vector(1, 1),
                new Vector(1, -1),
                new Vector(-1, 1),
                new Vector(-1, -1)
        );
    }
}
