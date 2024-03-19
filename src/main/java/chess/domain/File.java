package chess.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class File {

    private static final Pattern PATTERN = Pattern.compile("[a-h]");
    private static final String MINIMUM_FILE = "a";
    private static final String MAXIMUM_FILE = "h";

    private final String file;

    public File(String file) {
        validateRange(file);
        this.file = file;
    }

    private void validateRange(String file) {
        if (!PATTERN.matcher(file).matches()) {
            throw new IllegalArgumentException(
                    String.format("가로 위치는 %s ~ %s 사이의 값이어야 합니다.", MINIMUM_FILE, MAXIMUM_FILE));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        File file1 = (File) o;
        return Objects.equals(file, file1.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file);
    }
}
