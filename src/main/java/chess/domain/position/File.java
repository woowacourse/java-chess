package chess.domain.position;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public enum File {
    a,
    b,
    c,
    d,
    e,
    f,
    g,
    h;

    public static File from(String value) {
        try {
            return File.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("a~h까지 입력가능합니다.");
        }
    }

    public File update(int value) {
        int index = ordinal() + value;
        if(index >= values().length) {
            throw new IllegalArgumentException("보드판 밖으로 이동할 수 없습니다.");
        }
        return values()[index];
    }

    public int subtractFile(File file) {
        return ordinal() - file.ordinal();
    }

    public int findDirection(File file) {
        return Integer.compare(file.ordinal(), ordinal());
    }
}
