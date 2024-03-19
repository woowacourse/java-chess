package chess.domain;

import java.util.Arrays;

public enum File {

    a(0),
    b(1),
    c(2),
    d(3),
    e(4),
    f(5),
    g(6),
    h(7);


    static final String ERROR_NOT_EXIST_FILE = "존재하지 않는 파일입니다.";

    private final int file;

    File(int file) {
        this.file = file;
    }

    public static File from(String file) {
        validateExist(file);
        return valueOf(file);
    }

    private static void validateExist(String input) {
        if (Arrays.stream(values()).noneMatch(file -> file.name().equals(input))) {
            throw new IllegalArgumentException("[" + input + "] (은)는" + ERROR_NOT_EXIST_FILE);
        }
    }

    public int getFile() {
        return file;
    }
}
