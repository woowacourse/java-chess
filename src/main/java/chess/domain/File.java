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

    private static final Map<String, File> FILE_BY_VALUE = new HashMap<>();

    static {
        for (File file : values()) {
            FILE_BY_VALUE.put(file.value, file);
        }
    }

    private final String value;

    File(final String value) {
        this.value = value;
    }

    public static File findByValue(final String value) {
        return FILE_BY_VALUE.get(value);
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
