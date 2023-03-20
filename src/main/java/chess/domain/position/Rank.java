package chess.domain.position;

import java.util.Arrays;

public enum Rank {

    A(1, "1"),
    B(2, "2"),
    C(3, "3"),
    D(4, "4"),
    E(5, "5"),
    F(6, "6"),
    G(7, "7"),
    H(8, "8");

    private static final String WRONG_RANK_ERROR_MESSAGE = "잘못된 rank 입니다.";

    private final int address;
    private final String index;

    Rank(final int address, final String index) {
        this.address = address;
        this.index = index;
    }

    public static int findByIndex(final String index) {
        return Arrays.stream(values())
            .filter(rank -> rank.index.equalsIgnoreCase(index))
            .findAny()
            .map(rank -> rank.address)
            .orElseThrow(() -> new IllegalArgumentException(WRONG_RANK_ERROR_MESSAGE));
    }

}
