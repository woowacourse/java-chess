package domain.piece;

import domain.board.Position;

public class PositionArgument {

    static final Position from = Position.of(4, 4);
    static final Position UP = Position.of(4, 5);
    static final Position DOWN = Position.of(4, 3);
    static final Position LEFT = Position.of(3, 4);
    static final Position RIGHT = Position.of(5, 4);
    static final Position UP_LEFT = Position.of(3, 5);
    static final Position UP_RIGHT = Position.of(5, 5);
    static final Position DOWN_LEFT = Position.of(3, 3);
    static final Position DOWN_RIGHT = Position.of(5, 3);
    static final Position UP_UP_LEFT = Position.of(3, 6);
    static final Position UP_UP_RIGHT = Position.of(5, 6);
    static final Position RIGHT_RIGHT_UP = Position.of(6, 5);
    static final Position RIGHT_RIGHT_DOWN = Position.of(6, 3);
    static final Position DOWN_DOWN_LEFT = Position.of(3, 2);
    static final Position DOWN_DOWN_RIGHT = Position.of(5, 2);
    static final Position LEFT_LEFT_UP = Position.of(2, 5);
    static final Position LEFT_LEFT_DOWN = Position.of(2, 3);
}
