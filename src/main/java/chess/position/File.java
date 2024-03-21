package chess.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum File {
    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8);
    private final int value;

    File(int value) {
        this.value = value;
    }

    public FileDifference calculateDifference(File file) {
        return new FileDifference(file.value - value);
    }

    public List<File> getFileRoute(File to) {
        List<File> files = new ArrayList<>();
        if (value == to.value) {
            files.add(this);
            return files;
        }

        int unitDirection = (to.value - value) / Math.abs(to.value - value);
        for (int i = value + unitDirection; i != to.value; i += unitDirection) {
            files.add(File.of(i));
        }
        return files;
    }

    public static File of(int value) {
        return Arrays.stream(values())
                .filter(file -> file.value == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
