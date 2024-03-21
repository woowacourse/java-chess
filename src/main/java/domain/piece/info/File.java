package domain.piece.info;

import java.util.Arrays;
import java.util.List;

public enum File {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    public static final String FILE_NOT_FOUND = "해당하는 파일을 찾을 수 없습니다.";
    private final int index;

    File(final int index) {
        this.index = index;
    }

    public static File of(final String fileName) {
        return Arrays.stream(values())
                .filter(file -> file.name().equals(fileName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(FILE_NOT_FOUND));
    }

    public static File of(final int index) {
        return Arrays.stream(values())
                .filter(file -> file.index() == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(FILE_NOT_FOUND));
    }

    public int index() {
        return index;
    }

    public static List<File> rookPosition() {
        return List.of(A, H);
    }

    public static List<File> knightPosition() {
        return List.of(B, G);
    }

    public static List<File> bishopPosition() {
        return List.of(C, F);
    }

    public static List<File> pawnPosition() {
        return Arrays.asList(values());
    }

    public static List<File> kingPosition() {
        return List.of(E);
    }

    public static List<File> nonePosition() {
        return Arrays.asList(values());
    }

    public static List<File> queenPosition() {
        return List.of(D);
    }
}
