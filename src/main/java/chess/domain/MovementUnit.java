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

    //TODO 리팩토링
    public static MovementUnit direction(Spot spotA, Spot spotB) {
        int tempX = Math.abs(spotA.getX(spotB));
        int tempY = Math.abs(spotA.getY(spotB));

        if (spotA.equals(spotB)) {
            throw new IllegalArgumentException("올바른 움직임을 입력하세요");
        }

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

//        for (MovementUnit value : values()) {
//
//        }
    }
}