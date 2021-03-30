package chess.domain.moveStrategy;

import chess.domain.piece.Color;
import chess.domain.piece.Direction;

import java.util.Collections;
import java.util.List;

public class PawnDoubleMove extends DoubleMove {
    public PawnDoubleMove(Color color) {
        super(color);
    }

    public List<Direction> directions() {
        return Collections.singletonList(new Direction(0, color.moveUnit()));
    }

}
