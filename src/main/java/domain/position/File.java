package domain.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.lang.Math.min;

public enum File {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    private final int order;

    File(int order) {
        this.order = order;
    }

    public static File fromName(final String name) {
        try {
            return File.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("존재하지 않은 file입니다.");
        }
    }

    public static File fromOrder(final int order) {
        return Arrays.stream(values())
                .filter(file -> file.order == order)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 file입니다."));
    }

    public List<File> between(final File file) {
        final List<File> files = IntStream.range(min(this.order, file.order), max(this.order, file.order))
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

    int subtract(final File file) {
        return this.order - file.order;
    }

    public int order() {
        return order;
    }
}
