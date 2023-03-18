package chess.domain.move;

import java.util.ArrayList;
import java.util.List;

public enum Direction {
    RIGHT(Axis.HORIZON) {
        @Override
        public Direction flip() {
            return LEFT;
        }
    },
    UP(Axis.VERTICAL) {
        @Override
        public Direction flip() {
            return DOWN;
        }
    },
    LEFT(Axis.HORIZON) {
        @Override
        public Direction flip() {
            return RIGHT;
        }
    },
    DOWN(Axis.VERTICAL) {
        @Override
        public Direction flip() {
            return UP;
        }
    };

    private final Axis axis;

    Direction(Axis axis) {
        this.axis = axis;
    }

    public boolean isIn(Axis axis) {
        return this.axis == axis;
    }

    public Direction flipOver(Axis axis) {
        if (this.axis == axis) {
            return this;
        }
        return flip();
    }

    public abstract Direction flip();

    public List<Direction> repeat(long times) {
        List<Direction> directions = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            directions.add(this);
        }
        return directions;
    }
}
