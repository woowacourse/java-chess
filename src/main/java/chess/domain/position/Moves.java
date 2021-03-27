package chess.domain.position;

import java.util.function.Function;

public enum Moves {

    UP(Position::moveUp),
    DOWN(Position::moveDown),
    RIGHT(Position::moveRight),
    LEFT(Position::moveLeft),
    RIGHT_UP(Position::moveRightUp),
    LEFT_UP(Position::moveLeftUp),
    RIGHT_DOWN(Position::moveRightDown),
    LEFT_DOWN(Position::moveLeftDown),
    RIGHT_UP_UP(position -> position.moveRightUp().moveUp()),
    RIGHT_UP_RIGHT(position -> position.moveRightUp().moveRight()),
    LEFT_UP_UP(position -> position.moveLeftUp().moveUp()),
    LEFT_UP_LEFT(position -> position.moveLeftUp().moveLeft()),
    RIGHT_DOWN_DOWN(position -> position.moveRightDown().moveDown()),
    RIGHT_DOWN_RIGHT(position -> position.moveRightDown().moveRight()),
    LEFT_DOWN_DOWN(position -> position.moveLeftDown().moveDown()),
    LEFT_DOWN_LEFT(position -> position.moveLeftDown().moveLeft());

    private final Function<Position, Position> expression;

    Moves(Function<Position, Position> expression) {
        this.expression = expression;
    }

    public Position move(Position position) {
        return expression.apply(position);
    }

}
