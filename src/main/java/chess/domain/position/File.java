package chess.domain.position;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public enum File {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');
    private final char file;

    File(char file) {
        this.file = file;
    }

    public static File toFile(char candidate) {
        return Arrays.stream(File.values())
                .filter(file -> file.getFile() == candidate)
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }

    public int calculateFile(File other) {
        return file - other.getFile();
    }

    public char getFile() {
        return file;
    }

    public static boolean isFile(char candidate) {
        return Arrays.stream(File.values())
                .anyMatch(file -> file.getFile() == candidate);
    }

    public int calculateAbsoluteValue(File other) {
        return Math.abs(file - other.getFile());
    }

    public static List<File> getFiles(File from, File to) {
        return Arrays.stream(File.values())
                .filter(file -> file.getFile() > from.getFile() && file.getFile() < to.getFile())
                .collect(toList());
    }
}
