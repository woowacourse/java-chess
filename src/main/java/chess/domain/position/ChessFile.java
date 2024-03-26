package chess.domain.position;

import java.util.Arrays;

public enum ChessFile {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private static final String UNKNOWN_VALUE = "체스 파일 범위에 해당하지 않는 값입니다.";
    private static final String UNKNOWN_INDEX = "체스 파일 범위에 해당하지 않는 인덱스입니다.";

    private final String value;
    private final int index;

    ChessFile(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    public static int maxIndex() {
        return H.index;
    }

    public static ChessFile findByValue(final String fileValue) {
        return Arrays.stream(values())
                .filter(file -> file.value.equals(fileValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UNKNOWN_VALUE));
    }

    public static ChessFile findByIndex(final int fileIndex) {
        return Arrays.stream(values())
                .filter(file -> file.index == fileIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UNKNOWN_INDEX));
    }

    public ChessFile move(final Direction direction) {
        if (direction.isLeftSide()) {
            return findByIndex(this.index - 1);
        }
        if (direction.isVertical()) {
            return this;
        }
        return findByIndex(this.index + 1);
    }

    public int index() {
        return index;
    }

    public String value() {
        return value;
    }
}
