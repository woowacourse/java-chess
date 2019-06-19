package chess.domain;

public enum MovementUnit {
    UP(1, 0),
    RIGHT(0, 1),
    DIAGNOAL(1, 1),
    KNIGHT_MOVE_ONE(2, 1),
    KNIGHT_MOVE_TWO(1, 2);

    private final int x;
    private final int y;

    MovementUnit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static MovementUnit direction(Spot spotA, Spot spotB) {
        int tempX = spotA.getX(spotB);
        int tempY = spotA.getY(spotB);
        tempX /= Math.abs(tempX);
        tempY /= Math.abs(tempY);

        if (tempX == 0) {
            return RIGHT;
        }

        if (tempY == 0) {
            return UP;
        }

        if (tempX == tempY) {
            return DIAGNOAL;
        }

        if (tempX == 2 && tempY == 1) {
            return KNIGHT_MOVE_ONE;
        }

        if (tempX == 1 && tempY == 2) {
            return KNIGHT_MOVE_TWO;
        }

        throw new IllegalArgumentException("올바른 점들을 입력해주세요");
        //TODO 리팩토링
//        for (MovementUnit value : values()) {
//
//        }
    }
}