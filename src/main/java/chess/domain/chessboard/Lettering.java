package chess.domain.chessboard;

import java.util.Arrays;

public enum Lettering {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int value;

    Lettering(int value) {
        this.value = value;
    }

    public static Lettering findLettering(int value) {
        return Arrays.stream(Lettering.values())
                .filter(lettering -> lettering.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효한 넘버링 값이 아닙니다."));
    }

    public static Lettering findNextLettering(Lettering lettering) {
        if (lettering == Lettering.H) {
            throw new IllegalArgumentException();
        }
        int findLetteringValue = lettering.value + 1;
        return findLettering(findLetteringValue);
    }

    public static Lettering findPreviousLettering(Lettering lettering) {
        if (lettering == Lettering.A) {
            throw new IllegalArgumentException();
        }
        int findLetteringValue = lettering.value - 1;
        return findLettering(findLetteringValue);
    }
}
