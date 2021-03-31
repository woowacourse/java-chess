package chess.domain.moveStrategy;

import chess.domain.piece.Color;
import chess.domain.location.Vector;

import java.util.Collections;
import java.util.List;

public class PawnDoubleMove extends DoubleMove {
    public PawnDoubleMove(Color color) {
        super(color);
    }

    public List<Vector> directions() {
        return Collections.singletonList(new Vector(0, color.moveUnit()));
    }

}
