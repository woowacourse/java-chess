package chess.domain.position;

import java.util.Arrays;

public enum Rank {

    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private static final String WRONG_RANK_ERROR_MESSAGE = "잘못된 rank 입니다.";

    private final int address;
    private final String index;

    Rank(final int address, final String index) {
        this.address = address;
        this.index = index;
    }

    public static int findByIndex(String index) {
        Rank foundRank = Arrays.stream(values())
                .filter(rank -> rank.index.equalsIgnoreCase(index))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(WRONG_RANK_ERROR_MESSAGE));
        return foundRank.address;
    }

}
