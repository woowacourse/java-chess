package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.color.Color;

import java.util.Set;

import static chess.domain.move.Direction.*;

public final class King extends Piece {
    private static final Set<Direction> directions = Set.of(LEFT, RIGHT, UP, DOWN, LEFT_UP, LEFT_DOWN, RIGHT_DOWN, RIGHT_UP);

    public King(final Color color) {
        super(color);
    }

    @Override
    public String name() {
        String name = "k";
        if (super.team().equals(Color.WHITE)) {
            return name;
        }
        return name.toUpperCase();
    }

    @Override
    public boolean movable(final Direction direction) {
        return directions.contains(direction);
    }

    @Override
    public boolean movableByCount(final int count) {
        return count == 0;
    }
}
