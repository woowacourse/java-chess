package chess.board;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum File {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),
    ;

    private final int index;

    File(final int index) {
        this.index = index;
    }

    public static File from(final int index) {
        return Arrays.stream(values())
                .filter(file -> file.index == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("File의 index는 1~8이여야합니다."));
    }

    public static int calculateInterval(final File from, final File to) {
        return Math.abs(from.index - to.index);
    }

    public static List<File> sliceBetween(final File from, final File to) {
        final int min = Math.min(from.index, to.index);
        final int max = Math.max(from.index, to.index);

        return IntStream.rangeClosed(min, max)
                .mapToObj(File::from)
                .collect(Collectors.toList());
    }

    public int getIndex() {
        return index;
    }
}
