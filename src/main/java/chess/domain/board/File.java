package chess.domain.board;

import java.util.Arrays;
import java.util.function.Predicate;

public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String value;
    private final int x;

    File(String value, int x) {
        this.value = value;
        this.x = x;
    }

    public static File findValueOf(String fileInput) {
        return findFile((File file) -> file.value.equals(fileInput));
    }

    public static File findFile(Predicate<File> predicate) {
        return Arrays.stream(File.values())
            .filter(predicate)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 file값 입니다."));
    }

    public String getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public File move(Direction direction) {
        int targetX = x + direction.getX();
        return findFile(file -> file.x == targetX);
    }
}
