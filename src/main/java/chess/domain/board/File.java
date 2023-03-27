package chess.domain.board;

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

    private final String value;
    private final int file;

    File(String value, int file) {
        this.value = value;
        this.file = file;
    }

    public static File ofByValue(String inputValue) {
        return Arrays.stream(File.values())
                .filter(file -> file.value.equals(inputValue))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("범위를 벗어난 값입니다."));
    }

    public static File ofByFile(int inputFile) {
        return Arrays.stream(File.values())
                .filter(file -> file.file == inputFile)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("범위를 벗어난 값입니다."));
    }

    public int getFile() {
        return file;
    }
}
