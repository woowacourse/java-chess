package chess.domain;

import java.util.*;

public enum File {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private static final Map<String, File> FILE_BY_NAME = new HashMap<>();

    static {
        for (File file : values()) {
            FILE_BY_NAME.put(file.name, file);
        }
    }

    private final String name;

    File(final String name) {
        this.name = name;
    }

    public static File valueOfName(final String name) {
        validateFileName(name);
        return FILE_BY_NAME.get(name);
    }

    private static void validateFileName(final String name) {
        if (!FILE_BY_NAME.containsKey(name)) {
            throw new IllegalArgumentException("해당 위치는 존재하지 않습니다. 입력 값 :" + name);
        }
    }

    public int calculateDistance(final File otherFile) {
        return Math.abs(this.ordinal() - otherFile.ordinal());
    }

    public List<File> getFilesTo(final File otherFile) {
        final List<File> ascendingFiles = generateAscendingFiles(otherFile);
        if (this.ordinal() < otherFile.ordinal()) {
            return ascendingFiles;
        }
        return reverse(ascendingFiles);
    }

    private static List<File> reverse(final List<File> files) {
        Collections.reverse(files);
        return files;
    }

    private List<File> generateAscendingFiles(final File otherFile) {
        final int maxOrder = Math.max(this.ordinal(), otherFile.ordinal());
        final int minOrder = Math.min(this.ordinal(), otherFile.ordinal());

        final List<File> passingFiles = new ArrayList<>();
        for (int order = minOrder + 1; order < maxOrder; order++) {
            passingFiles.add(findByOrdinal(order));
        }
        return passingFiles;
    }

    private File findByOrdinal(final int order) {
        return values()[order];
    }
}
