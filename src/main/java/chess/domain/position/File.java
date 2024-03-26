package chess.domain.position;

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

    public int calculateDifferenceTo(File file) {
        return file.value - value;
    }

    public List<File> findFileRouteToTargetFile(File to) {
        if (to.value > value) {
            return inclineDirectionRoute(to);
        }
        return declineDirectionRoute(to);
    }

    private List<File> inclineDirectionRoute(File to) {
        List<File> files = new ArrayList<>();
        for (int i = value + 1; i < to.value; i++) {
            files.add(File.of(i));
        }
        return files;
    }

    private List<File> declineDirectionRoute(File to) {
        List<File> files = new ArrayList<>();
        for (int i = value - 1; i > to.value; i--) {
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

    public static File of(String name) {
        return Arrays.stream(values())
                .filter(file -> file.name().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
