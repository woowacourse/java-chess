package chess.domain.square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum File {

    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8),
    ;

    private static final String ERROR_NOT_EXIST_FILE = "은(는) 존재하지 않는 파일입니다.";

    private final int index;

    File(final int index) {
        this.index = index;
    }

    public static File findByValue(final String value) {
        return Arrays.stream(values())
                .filter(file -> file.name().equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(value + ERROR_NOT_EXIST_FILE));
    }

    public int calculateDistance(final File other) {
        return Math.abs(index - other.index);
    }

    public List<File> findFilePath(final File other) {
        final int start = Math.min(index, other.index) + 1;
        final int end = Math.max(index, other.index);

        final List<File> filePath = new ArrayList<>();
        for (int i = start; i < end; i++) {
            filePath.add(valueOfIndex(i));
        }

        if (start != index) {
            Collections.reverse(filePath);
        }
        return filePath;
    }

    private File valueOfIndex(final int value) {
        return Arrays.stream(values())
                .filter(file -> file.index == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(value + ERROR_NOT_EXIST_FILE));
    }

    public int getIndex() {
        return index;
    }
}
