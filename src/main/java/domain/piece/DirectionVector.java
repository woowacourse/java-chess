package domain.piece;

public enum DirectionVector {
    TOP(Vector.of(0, 1)), TOP_RIGHT(Vector.of(1, 1)),
    RIGHT(Vector.of(1, 0)), BOTTOM_RIGHT(Vector.of(1, -1)),
    BOTTOM(Vector.of(0, -1)), BOTTOM_LEFT(Vector.of(-1, -1)),
    LEFT(Vector.of(-1, 0)), TOP_LEFT(Vector.of(-1, 1));

    private final Vector vector;

    DirectionVector(Vector vector) {
        this.vector = vector;
    }

    public Vector multiply(int length) {
        return vector.multiply(length);
    }

    public boolean isSameDirection(Vector vector) {
        return this.multiply(vector.getMaxLength()).equals(vector);
    }


}
