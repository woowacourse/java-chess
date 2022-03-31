package chess.domain.board.position;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum File {

    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private static final String INVALID_IDX_RANGE_EXCEPTION_MESSAGE = "존재하지 않는 행입니다. (범위: a~h)";

    private final String key;
    private final int value;

    File(String key, int value) {
        this.key = key;
        this.value = value;
    }

    static File of(String key) {
        return Arrays.stream(values())
                .filter(file -> Objects.equals(file.key, key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_IDX_RANGE_EXCEPTION_MESSAGE));
    }

    public static List<File> allFilesAscending() {
        return List.of(values());
    }

    int valueDifference(File targetFile) {
        return targetFile.value - value;
    }

    File oneFileToward(File targetFile) {
        int incrementedIdx = incrementToward(targetFile);
        return Arrays.stream(values())
                .filter(file -> file.value == incrementedIdx)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_IDX_RANGE_EXCEPTION_MESSAGE));

    }

    private int incrementToward(File targetFile) {
        int from = this.value;
        int to = targetFile.value;
        return from + Integer.compare(to, from);
    }

    String key() {
        return key;
    }

    @Override
    public String toString() {
        return "File{" + "key='" + key + '\'' + ", value=" + value + '}';
    }
}
