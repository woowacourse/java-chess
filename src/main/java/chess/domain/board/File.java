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

    private static final String NO_FILE_MATCHED = "File 입력을 확인해주세요 (a-h 입력 가능)";
    private static final String NO_MATCHED_FILE_FOUND_BY_POSITION = "해당 포지션의 File을 찾지 못했습니다";

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
                .orElseThrow(() -> new NoSuchElementException(NO_FILE_MATCHED));
    }

    public static File from(Position position) {
        return Arrays.stream(values())
                .filter(position::isSameFile)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(NO_MATCHED_FILE_FOUND_BY_POSITION));
    }

    public int dx(File another) {
        return another.fileNumber - this.fileNumber;
    }

    public List<File> between(File target) {
        File higherFile = getHigherFile(target);
        File lowerFile = getLowerFile(target);

        final List<File> fileBetweens = Arrays.stream(values())
                .filter(file -> (file.fileNumber > lowerFile.fileNumber) && (file.fileNumber < higherFile.fileNumber))
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

    public String getFileName() {
        return fileName;
    }
}
