package domain;

import domain.board.position.Vector;

public class VectorFixture {
    public static final Vector UP = Vector.of(0, 1);
    public static final Vector UP_RIGHT = Vector.of(1, 1);
    public static final Vector RIGHT = Vector.of(1, 0);
    public static final Vector RIGHT_DOWN = Vector.of(1, -1);
    public static final Vector DOWN = Vector.of(0, -1);
    public static final Vector DOWN_LEFT = Vector.of(-1, -1);
    public static final Vector LEFT = Vector.of(-1, 0);
    public static final Vector LEFT_UP = Vector.of(-1, 1);
    public static final Vector UP_UP = Vector.of(0, 2);
    public static final Vector DOWN_DOWN = Vector.of(0, -2);
}
