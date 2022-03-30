package chess.domain.board;

import java.util.Arrays;

public enum File {

    FILE_A("a", 1),
    FILE_B("b", 2),
    FILE_C("c", 3),
    FILE_D("d", 4),
    FILE_E("e", 5),
    FILE_F("f", 6),
    FILE_G("g", 7),
    FILE_H("h", 8),
    ;

    private final String value;
    private final int x;

    File(String value, int x) {
        this.value = value;
        this.x = x;
    }

    public static File of(String input) {
        return Arrays.stream(File.values())
                .filter(file -> file.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않는 입력값입니다."));
    }

    public File move(int x) {
        return findX(this.x + x);
    }

    public boolean canMove(int x) {
        try {
            findX(this.x + x);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private File findX(int input) {
        return Arrays.stream(File.values())
                .filter(file -> file.x == input)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않는 입력값입니다."));
    }

    @Override
    public String toString() {
        return "Column{" +
                "x=" + x +
                '}';
    }
}
