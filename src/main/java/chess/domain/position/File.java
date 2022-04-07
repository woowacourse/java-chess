package chess.domain.position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    private final String name;
    private final int order;

    File(final String name, final int order) {
        this.name = name;
        this.order = order;
    }

    public static File from(final String name) {
        return Arrays.stream(File.values())
                .filter(file -> file.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스판 범위를 벗어납니다."));
    }

    public static File findByOrder(final int order) {
        return Arrays.stream(File.values())
                .filter(file -> file.order == order)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스판 범위를 벗어납니다."));
    }

    public static List<File> sorted() {
        return Arrays.stream(File.values())
                .sorted(Comparator.comparing(File::getName))
                .collect(Collectors.toUnmodifiableList());
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }
}
