package chess.domain.position;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8),
    ;

    private static final int START_EXCLUSIVE = 1;

    private final String command;
    private final int position;

    File(final String command, final int position) {
        this.command = command;
        this.position = position;
    }

    private static File from(final int position) {
        return Arrays.stream(values())
                .filter(value -> value.position == position)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("위치 값은 1 ~ 8 사이의 값이어야 합니다."));
    }

    public List<File> between(final File file) {
        final List<File> result = IntStream.range(min(position, file.position), max(position, file.position))
                .skip(START_EXCLUSIVE)
                .mapToObj(File::from)
                .collect(toList());
        if (position > file.position) {
            Collections.reverse(result);
        }
        return result;
    }

    public int calculateGap(final File target) {
        return position - target.position;
    }

    public String command() {
        return command;
    }
}
