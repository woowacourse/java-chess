package chess.domain.position;

import java.util.function.UnaryOperator;

public enum Moves {
    UP(position -> position.move(0, 1)),
    DOWN(position -> position.move(0, -1)),
    RIGHT(position -> position.move(1, 0)),
    LEFT(position -> position.move(-1, 0)),
    RIGHT_UP(position -> position.move(1, 1)),
    LEFT_UP(position -> position.move(-1, 1)),
    RIGHT_DOWN(position -> position.move(1, -1)),
    LEFT_DOWN(position -> position.move(-1, -1)),
    RIGHT_UP_UP(position -> position.move(1, 2)),
    RIGHT_UP_RIGHT(position -> position.move(2, 1)),
    LEFT_UP_UP(position -> position.move(-1, 2)),
    LEFT_UP_LEFT(position -> position.move(-2, 1)),
    RIGHT_DOWN_DOWN(position -> position.move(1, -2)),
    RIGHT_DOWN_RIGHT(position -> position.move(2, -1)),
    LEFT_DOWN_DOWN(position -> position.move(-1, -2)),
    LEFT_DOWN_LEFT(position -> position.move(-2, -1));

    private final UnaryOperator<Position> expression;

    Moves(UnaryOperator<Position> expression) {
        this.expression = expression;
    }

    public Position move(Position position) {
        return expression.apply(position);
    }

}
