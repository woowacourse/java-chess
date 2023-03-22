package domain.position;

import java.util.Arrays;

public enum Rank {
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1"),
    NOTHING(" ");

    private String name;

    Rank(final String name) {
        this.name = name;
    }

    public Rank move(int distance) {
        final String resultRank = String.valueOf((char) (name.charAt(0) + distance));

        return Arrays.stream(Rank.values())
                .filter(rank -> rank.name.equals(resultRank))
                .findAny()
                .orElse(NOTHING);
    }

    public int getDifference(final Rank other) {
        return other.name.charAt(0) - this.name.charAt(0);
    }

    public String getName() {
        return name;
    }
}
