package chess.domain.board;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    U(0, 1),
    D(0, -1),
    R(1, 0),
    L(-1, 0),

    UR(1, 1),
    UL(-1, 1),
    DR(1, -1),
    DL(-1, -1),

    UUR(1, 2),
    UUL(-1, 2),
    URR(2, 1),
    ULL(-2, 1),

    DDR(1, -2),
    DDL(-1, -2),
    DRR(2, -1),
    DLL(-2, -1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction of(int x, int y) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.equals(x, y))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 체스 기물이 움직일 수 없는 방향입니다."));
    }

    public boolean isForward() {
        return List.of(D, U).contains(this);
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
