package chess.domain.state;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    D(0, -1),
    DR(1, -1),
    DL(-1, -1),

    U(0, 1),
    UR(1, 1),
    UL(-1, 1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean isBlackPawnDirection(Direction direction) {
        return List.of(D, DR, DL).contains(direction);
    }

    public static boolean isWhitePawnDirection(Direction direction) {
        return List.of(U, UR, UL).contains(direction);
    }

    public static Direction of(int x, int y) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.equals(x, y))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 체스 기물이 움직일 수 없는 방향입니다."));
    }

    public static boolean isForward(Direction direction) {
        return List.of(D, U).contains(direction);
    }

    private boolean equals(int x, int y) {
        return this.x == x && this.y == y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
