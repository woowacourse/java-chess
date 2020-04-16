package chess.model.domain.board;

import java.util.Arrays;

public enum Rank {
    FIRST("1", 1),
    SECOND("2", 2),
    THIRD("3", 3),
    FOURTH("4", 4),
    FIFTH("5", 5),
    SIXTH("6", 6),
    SEVENTH("7", 7),
    EIGHTH("8", 8);

    private static final String NO_RANK_EXCEPTION_MESSAGE = "Rank가 존재하지 않습니다.";

    private final String name;
    private final int number;

    Rank(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public boolean hasNextIncrement(int increment) {
        return Arrays.stream(Rank.values())
            .anyMatch(rank -> rank.number == this.number + increment);
    }

    public Rank findIncrement(int increment) throws IllegalArgumentException {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.number == this.number + increment)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(NO_RANK_EXCEPTION_MESSAGE));
    }
}
