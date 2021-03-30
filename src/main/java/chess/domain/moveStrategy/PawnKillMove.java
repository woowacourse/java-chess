package chess.domain.moveStrategy;

import chess.domain.piece.Color;
import chess.domain.piece.Direction;

import java.util.Arrays;
import java.util.List;

public class PawnKillMove extends SingleMove {
    public PawnKillMove(Color color) {
        super(color);
    }

    @Override
    public List<Direction> directions() {
        return Arrays.asList(
                new Direction(1, color.moveUnit()),
                new Direction(-1, color.moveUnit())
        );
    }
}
