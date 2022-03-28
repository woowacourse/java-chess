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

    public static boolean isBlackPawnDirection(Direction direction) {
        return List.of(D, DR, DL).contains(direction);
    }

    public static boolean isWhitePawnDirection(Direction direction) {
        return List.of(U, UR, UL).contains(direction);
    }

    public static boolean isRookDirection(Direction direction) {
        return isCardinalDirection(direction);
    }

    public static boolean isKnightDirection(Direction direction) {
        return List.of(UUR, UUL, URR, ULL, DDR, DDL, DRR, DLL).contains(direction);
    }

    public static boolean isBishopDirection(Direction direction) {
        return isDiagonalDirection(direction);
    }

    public static boolean isQueenDirection(Direction direction) {
        return isEveryDirection(direction);
    }

    public static boolean isKingDirection(Direction direction) {
        return isEveryDirection(direction);
    }

    private static boolean isEveryDirection(Direction direction) {
        return isDiagonalDirection(direction) || isCardinalDirection(direction);
    }

    private static boolean isDiagonalDirection(Direction direction) {
        return List.of(UR, UL, DR, DL).contains(direction);
    }

    private static boolean isCardinalDirection(Direction direction) {
        return List.of(U, D, R, L).contains(direction);
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
