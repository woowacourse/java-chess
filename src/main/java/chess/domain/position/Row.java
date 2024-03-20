package chess.domain.position;

import java.util.Arrays;

public enum Row {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int southToNorth;

    Row(int southToNorth) {
        this.southToNorth = southToNorth;
    }

    public Row toNorth() {
        if (southToNorth >= 8) {
            throw new IllegalStateException("북쪽으로 이동할 수 없습니다.");
        }
        return find(southToNorth + 1);
    }

    public Row toSouth() {
        if (southToNorth <= 1) {
            throw new IllegalStateException("남쪽으로 이동할 수 없습니다.");
        }
        return find(southToNorth - 1);
    }

    private Row find(int southToNorth) {
        return Arrays.stream(Row.values())
                .filter(row -> row.southToNorth == southToNorth)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 가로 위치가 없습니다."));
    }
}
