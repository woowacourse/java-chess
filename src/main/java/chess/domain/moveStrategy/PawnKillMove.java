package chess.domain.moveStrategy;

import chess.domain.piece.Color;
import chess.domain.piece.Vector;

import java.util.Arrays;
import java.util.List;

public class PawnKillMove extends SingleMove {
    public PawnKillMove(Color color) {
        super(color);
    }

    @Override
    public List<Vector> directions() {
        return Arrays.asList(
                new Vector(1, color.moveUnit()),
                new Vector(-1, color.moveUnit())
        );
    }
}
