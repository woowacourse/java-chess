package chess.domain.position;

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

    public File moveHorizontal(int index) {
        List<File> files = List.of(File.values());
        int targetIndex = files.indexOf(this) + index;

        validateIndexBound(targetIndex, files);

        return files.get(targetIndex);
    }

    private void validateIndexBound(int targetIndex, List<File> files) {
        if (targetIndex < 0 || files.size() <= targetIndex) {
            throw new IndexOutOfBoundsException("더 이상 이동할 수 없습니다.");
        }
    }

    public int calculateDiff(File file) {
        List<File> files = List.of(File.values());

        return files.indexOf(this) - files.indexOf(file);
    }
}
