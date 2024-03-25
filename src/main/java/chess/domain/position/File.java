package chess.domain.position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum File {

    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8),
    ;

    private final String symbol;
    private final int index;

    File(String symbol, int index) {
        this.symbol = symbol;
        this.index = index;
    }

    private static final String OUT_OF_RANGE_ERROR = "더 이상 이동할 수 없습니다.";

    public File moveHorizontal(int index) {
        List<File> files = List.of(File.values());
        int targetIndex = files.indexOf(this) + index;

        validateIndexBound(targetIndex, files);

        return files.get(targetIndex);
    }

    private void validateIndexBound(int targetIndex, List<File> files) {
        if (targetIndex < 0 || files.size() <= targetIndex) {
            throw new IndexOutOfBoundsException(OUT_OF_RANGE_ERROR);
        }
    }

    public int calculateDiff(File file) {
        List<File> files = sorted();

        return files.indexOf(file) - files.indexOf(this);
    }

    public String symbol() {
        return symbol;
    }

    public int index() {
        return index;
    }

    public static List<File> sorted() {
        return Arrays.stream(File.values())
                .sorted(Comparator.comparing(File::index))
                .toList();
    }
}
