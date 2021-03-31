package chess.domain.moveStrategy;

import chess.domain.location.Vector;
import chess.domain.piece.Color;

import java.util.Collections;
import java.util.List;

public class PawnSingleMove extends SingleMove {
    public PawnSingleMove(Color color) {
        super(color);
    }

    @Override
    public List<Vector> directions() {
        return Collections.singletonList(new Vector(0, color.moveUnit()));
    }
}
