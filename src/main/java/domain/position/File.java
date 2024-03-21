package domain.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum File {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private final String name;
    private final int order;

    File(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public static File fromName(String name) {
        return Arrays.stream(values())
                .filter(file -> file.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("rejected value: %s - 존재하지 않은 file입니다.", name)));
    }

    public static File fromOrder(int order) {
        return Arrays.stream(values())
                .filter(file -> file.order == order)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("rejected value: %d - 존재하지 않은 file입니다.", order)));
    }

    public List<File> between(final File file) {
        final List<File> files = IntStream.range(Math.min(this.order, file.order), Math.max(this.order, file.order))
                .skip(1)
                .mapToObj(File::fromOrder)
                .collect(Collectors.toList());
        if (this.isLaterThan(file)) {
            Collections.reverse(files);
        }
        return files;
    }

    private boolean isLaterThan(final File file) {
        return this.order > file.order;
    }

    public int subtract(File file) {
        return this.order - file.order;
    }

    public int order() {
        return order;
    }
}
