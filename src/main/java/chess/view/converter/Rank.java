package chess.view.converter;

import java.util.Arrays;

public enum Rank {

    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8);

    private static final String INVALID_RANK_INPUT = "Rank는 1~8 사이에서 입력해야 합니다.";

    private final String input;
    private final int row;

    Rank(String input, int row) {
        this.input = input;
        this.row = row;
    }

    static Rank from(String input) {
        return Arrays.stream(values())
                .filter(rank -> rank.input.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK_INPUT));
    }

    public int getRow() {
        return row;
    }
}
