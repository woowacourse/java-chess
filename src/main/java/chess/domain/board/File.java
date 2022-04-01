package chess.domain.board;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public enum File {
    A("A", 1),
    B("B", 2),
    C("C", 3),
    D("D", 4),
    E("E", 5),
    F("F", 6),
    G("G", 7),
    H("H", 8);

    private final String fileName;
    private final int fileNumber;

    File(String fileName, int fileNumber) {
        this.fileName = fileName;
        this.fileNumber = fileNumber;
    }

    public static File from(String fileInput) {
        return Arrays.stream(values())
                .filter(file -> file.fileName.equalsIgnoreCase(fileInput))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public int dx(File another) {
        return another.fileNumber - this.fileNumber;
    }

    public List<File> between(File target) {
        File higherFile = getHigherFile(target);
        File lowerFile = getLowerFile(target);

        final List<File> fileBetweens = Arrays.stream(values())
                .filter(file -> file.fileNumber > lowerFile.fileNumber && higherFile.fileNumber < target.fileNumber)
                .collect(Collectors.toList());

        return order(fileBetweens, target);
    }

    private File getHigherFile(File target) {
        if (this.fileNumber > target.fileNumber) {
            return this;
        }

        return target;
    }

    private File getLowerFile(File target) {
        if (this.fileNumber < target.fileNumber) {
            return this;
        }

        return target;
    }

    private List<File> order(List<File> files, File target) {
        if (this.fileNumber > target.fileNumber) {
            return files.stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
        }

        return files;
    }

    public static File from(Position position) {
        return Arrays.stream(values())
                .filter(position::isSameFile)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
