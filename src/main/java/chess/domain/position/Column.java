package chess.domain.position;

import java.util.Arrays;

public enum Column {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private static final int MAX_COLUMN = 8;
    private static final int MIN_COLUMN = 1;

    private final int value;

    Column(int value) {
        this.value = value;
    }

    public static Column of(String value) {
        return Arrays.stream(values())
                .filter(column -> column.name()
                        .equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 열 이름이 들어왔습니다."));
    }

    public static Column of(int index) {
        return Arrays.stream(values())
                .filter(column -> column.value == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 인덱스의 열은 존재하지 않습니다."));
    }

    public static int calculateDifference(Column source, Column target) {
        return source.value - target.value;
    }

    public Column move(int value) {
        int indexAfterMove = this.value + value;
        if (indexAfterMove > MAX_COLUMN || indexAfterMove < MIN_COLUMN) {
            throw new IndexOutOfBoundsException("범위를 벗어났습니다.");
        }
        return of(indexAfterMove);
    }
}
