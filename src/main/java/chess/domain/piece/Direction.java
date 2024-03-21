package chess.domain.piece;

import java.util.Arrays;

public enum Direction {

    LEFT(-1,0),
    RIGHT(1,0),
    UP(0,1),
    DOWN(0,-1),
    LEFT_UP(-1,1),
    LEFT_DOWN(-1, -1),
    RIGHT_UP(1,1),
    RIGHT_DOWN(1,-1),
    KNIGHT_LEFT_UP(-2, 1),
    KNIGHT_LEFT_DOWN(-2, -1),
    KNIGHT_RIGHT_UP(2, 1),
    KNIGHT_RIGHT_DOWN(2, -1),
    KNIGHT_UP_LEFT(-1, 2),
    KNIGHT_UP_RIGHT(1, 2),
    KNIGHT_DOWN_LEFT(-1, -2),
    KNIGHT_DOWN_RIGHT(1, -2),
    ;

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // TODO: 이름 고민 필요
    public static Direction find(final int dx, final int dy) {
        // TODO: 메서드 분리
        return Arrays.stream(values())
                // TODO: 같은 결과값을 갖는게 여러개
                .filter(direction -> direction.isSameGradiant(dx, dy) && direction.isSameSign(dx, dy))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 이동 방향입니다."));
    }

    // TODO: 이름 고민 필요
    public int calculateNextX(final int currentX) {
        return currentX + this.x;
    }

    // TODO: 이름 고민 필요
    public int calculateNextY(final int currentY) {
        return currentY + this.y;
    }

    // TODO: 개선 해보자
    private boolean isSameGradiant(int dx, int dy) {
        return (double) this.x / this.y == (double) dx / dy;
    }

    // TODO: 개선 해보자
    private boolean isSameSign(int dx, int dy) {
        return this.x * dx >= 0 && this.y * dy >= 0;
    }
}
