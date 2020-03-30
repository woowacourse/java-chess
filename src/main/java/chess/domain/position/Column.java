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

    private final int index;

    Column(int index) {
        this.index = index;
    }

    public Column plus(int number) {
        return Column.of(this.index + number);
    }

    private static Column of(int number) {
        return Arrays.stream(Column.values())
                .filter(column -> column.index == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("파일의 범위를 초과하였습니다."));
    }
}
