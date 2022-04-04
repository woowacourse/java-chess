package chess.domain.board;

public enum UnitDirectVector {
    TOP(0, 1),
    BOTTOM(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0),

    TOP_LEFT(-1, 1),
    TOP_RIGHT(1, 1),
    BOTTOM_LEFT(-1, -1),
    BOTTOM_RIGHT(1, -1),

    TOP_TOP_LEFT(-1, 2),
    TOP_TOP_RIGHT(1, 2),
    BOTTOM_BOTTOM_LEFT(-1, -2),
    BOTTOM_BOTTOM_RIGHT(1, -2),

    LEFT_LEFT_TOP(-2, 1),
    LEFT_LEFT_DOWN(-2, -1),
    RIGHT_RIGHT_TOP(2, 1),
    RIGHT_RIGHT_DOWN(2, -1),
    ;

    private final int x;
    private final int y;

    UnitDirectVector(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isSameUnitDirectVector(final Positions positions) {
        final int yVector = positions.calculateDirectedRowDistance();
        final int xVector = positions.calculateDirectedColumnDistance();
        System.out.println("b->a의 단위방향벡터계산값 : Math.atan2(yVector, xVector) = " + Math.atan2(yVector, xVector));
        System.out.println("가능한방향(" + this.name() + ")의 단위방향벡터값 계산: Math.atan2(y, x) = " + Math.atan2(y, x));
        return Math.atan2(y, x) == Math.atan2(yVector, xVector);
    }

    public int nextColumnNumber(final int number) {
        return x + number;
    }

    public int nextRowNumber(final int number) {
        return y + number;
    }
}
