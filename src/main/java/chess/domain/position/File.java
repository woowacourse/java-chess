package chess.domain.position;

import java.util.Arrays;
import java.util.List;

public enum File {

    a,
    b,
    c,
    d,
    e,
    f,
    g,
    h,
    ;

    private static final String OUT_OF_RANGE_ERROR = "더 이상 이동할 수 없습니다.";
    private static final String FILE_NOT_FOUND_ERROR = "존재하지 않는 파일입니다.";

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
        List<File> files = List.of(File.values());

        return files.indexOf(file) - files.indexOf(this);
    }

    public static File findFileByName(String name) {
        return Arrays.stream(File.values())
                .filter(file -> file.name().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(FILE_NOT_FOUND_ERROR));
    }
}
