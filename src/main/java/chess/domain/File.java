package chess.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum File {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private static final Map<Integer, File> FILE_BY_INDEX = new HashMap<>();
    private static final Map<String, File> FILE_BY_VALUE = new HashMap<>();

    static {
        for (File file : values()) {
            FILE_BY_INDEX.put(file.index, file);
            FILE_BY_VALUE.put(file.value, file);
        }
    }

    private final String value;
    private final int index;

    File(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    public static File findByValue(final String value) {
        return FILE_BY_VALUE.get(value);
    }

    public int calculateDistance(final File otherFile) {
        return Math.abs(this.index - otherFile.index);
    }

    public List<File> getFilesTo(final File otherFile) {
        final List<File> ascendingFiles = generateAscendingFiles(otherFile);
        if (this.index < otherFile.index) {
            return ascendingFiles;
        }
        return reverse(ascendingFiles);
    }

    private static List<File> reverse(final List<File> files) {
        Collections.reverse(files);
        return files;
    }

    private List<File> generateAscendingFiles(final File otherFile) {
        final int maxIndex = Math.max(this.index, otherFile.index);
        final int minIndex = Math.min(this.index, otherFile.index);

        final List<File> passingFiles = new ArrayList<>();
        for (int index = minIndex + 1; index < maxIndex; index++) {
            passingFiles.add(findByIndex(index));
        }
        return passingFiles;
    }

    private File findByIndex(final int index) {
        return FILE_BY_INDEX.get(index);
    }
}
