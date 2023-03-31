package chess.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum File {
    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private final int position;
    private final String symbol;

    File(final int position, final String symbol) {
        this.position = position;
        this.symbol = symbol;
    }

    public static File from(final String symbol) {
        return Arrays.stream(values())
                .filter(file -> Objects.equals(symbol, file.symbol))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일입니다"));
    }

    public static List<File> findFilesBetween(final File from, final File to) {
        final int min = Math.min(from.position, to.position);
        final int max = Math.max(from.position, to.position);
        List<File> files = Arrays.stream(values())
                .filter(file -> file.position > min && file.position < max)
                .collect(Collectors.toList());
        if (from.position == max) {
            Collections.reverse(files);
        }
        return files;
    }

    public int calculateDistanceTo(final File other) {
        return Math.abs(position - other.position);
    }

    public int getPosition() {
        return position;
    }

    public String getSymbol() {
        return symbol;
    }
}
