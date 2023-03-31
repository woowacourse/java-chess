package chess.domain.square;

import java.util.Arrays;

public enum File {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private final String file;
    private final int index;

    File(final String file, final int index) {
        this.file = file;
        this.index = index;
    }

    public static int calculate(final File source, final File destination) {
        return source.index - destination.index;
    }

    public File next(final int direction) {
        return findFileBy(this.index + direction);
    }

    public static File findFileBy(final String fileInput) {
        return Arrays.stream(File.values())
                .filter(value -> value.file.equals(fileInput))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 File을 입력했습니다."));
    }

    private File findFileBy(final int fileIndex) {
        return Arrays.stream(File.values())
                .filter(value -> value.index == fileIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 File index를 입력했습니다."));
    }

    public String getFile() {
        return file;
    }
}
