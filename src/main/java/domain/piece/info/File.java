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

    private final int index;

    File(final int index) {
        this.index = index;
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
