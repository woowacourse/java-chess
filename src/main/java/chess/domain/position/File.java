package chess.domain.position;

import java.util.Arrays;

public enum File {
    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8);

    private int index;

    File(int index) {
        this.index = index;
    }

    public File plus(int number) {
        return File.of(this.index + number);
    }

    private static File of(int number) {
        return Arrays.stream(File.values())
                .filter(file -> file.index == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("파일의 범위를 초과하였습니다."));
    }

    public File reverse() {
        return File.of(values().length - index);
    }
}
