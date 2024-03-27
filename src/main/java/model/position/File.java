package model.position;

import model.direction.Direction;

import java.util.Arrays;

public enum File {
    A('a', 1),
    B('b', 2),
    C('c', 3),
    D('d', 4),
    E('e', 5),
    F('f', 6),
    G('g', 7),
    H('h', 8);

    private final char expression;
    private final int index;

    File(char expression, int index) {
        this.expression = expression;
        this.index = index;
    }

    public static File from(char expression) {
        return Arrays.stream(values())
                     .filter(file -> file.expression == expression)
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("일치하는 File이 존재하지 않습니다."));
    }

    public boolean canMoveTo(Direction direction) {
        int movedIndex = index + direction.fileDifferential();
        return A.index <= movedIndex && movedIndex <= H.index;
    }

    public File moving(Direction direction) {
        return Arrays.stream(values())
                     .filter(file -> file.index == index + direction.fileDifferential())
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("현재 File에서 해당 방향으로 이동할 수 없습니다."));
    }

    public int index() {
        return this.index;
    }
}
