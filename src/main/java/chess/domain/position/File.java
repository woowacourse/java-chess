package chess.domain.position;

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

    private int index;

    File(int index) {
        this.index = index;
    }

    public static File from(String value) {
        try {
            return File.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("a~h까지 입력가능합니다.");
        }
    }

    public File update(int value) {
        int index = ordinal() + value;
        if (index >= values().length) {
            throw new IllegalArgumentException("보드판 밖으로 이동할 수 없습니다.");
        }
        return findFile(index);
    }

    private static File findFile(int index) {
        return Arrays.stream(values())
                .filter(file -> file.index == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("a~h까지 가능합니다."));
    }

    public int subtractFile(File file) {
        return ordinal() - file.ordinal();
    }

    public int findDirection(File file) {
        return Integer.compare(file.ordinal(), ordinal());
    }
}
