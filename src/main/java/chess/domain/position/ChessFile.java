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

    private final String value;
    private final int index;

    ChessFile(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public static int maxIndex() {
        return H.index;
    }

    public static ChessFile findByValue(String fileValue) {
        return Arrays.stream(values())
                .filter(file -> file.value.equals(fileValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스 파일 범위에 해당하지 않는 값입니다."));
    }

    public static ChessFile findByIndex(int fileIndex) {
        return Arrays.stream(values())
                .filter(file -> file.index == fileIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스 파일 범위에 해당하지 않는 인덱스입니다."));
    }

    public int index() {
        return index;
    }

    public String value() {
        return value;
    }
}
