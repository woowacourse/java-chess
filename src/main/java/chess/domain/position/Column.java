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

    private final int value;

    Column(final int value) {
        this.value = value;
    }

    public static Column of(final String value) {
        return Arrays.stream(values())
                .filter(column -> column.name()
                        .equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 열 이름이 들어왔습니다."));
    }

    public static Column of(final int index) {
        return Arrays.stream(values())
                .filter(column -> column.value == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 인덱스의 열은 존재하지 않습니다."));
    }

    public Column move(final int value) {
        int indexAfterMove = this.value + value;
        if (indexAfterMove > H.value || indexAfterMove < A.value) {
            throw new IndexOutOfBoundsException("범위를 벗어났습니다.");
        }
        return of(indexAfterMove);
    }
}
