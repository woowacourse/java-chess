package domain.piece;

public enum DirectionVector {
    TOP(Vectorr.of(0, 1)), TOP_RIGHT(Vectorr.of(1, 1)),
    RIGHT(Vectorr.of(1, 0)), BOTTOM_RIGHT(Vectorr.of(1, -1)),
    BOTTOM(Vectorr.of(0, -1)), BOTTOM_LEFT(Vectorr.of(-1, -1)),
    LEFT(Vectorr.of(-1, 0)), TOP_LEFT(Vectorr.of(-1, 1));

    private final Vectorr vectorr;

    DirectionVector(Vectorr vectorr) {
        this.vectorr = vectorr;
    }

    public boolean isSameDirection(Vectorr vector) {
        return this.multiply(vector.getMaxLength()).equals(vector);
    }

    public Vectorr multiply(int length) {
        return vectorr.multiply(length);
    }
}
