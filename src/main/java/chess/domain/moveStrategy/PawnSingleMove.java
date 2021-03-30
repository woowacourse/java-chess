package chess.domain.moveStrategy;

import chess.domain.piece.Color;
import chess.domain.piece.Direction;

import java.util.Collections;
import java.util.List;

public class PawnSingleMove extends SingleMove {
    public PawnSingleMove(Color color) {
        super(color);
    }

    @Override
    public List<Direction> directions() {
        return Collections.singletonList(new Direction(0, color.moveUnit()));
    }
}
