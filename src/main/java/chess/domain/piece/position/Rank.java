package chess.domain.piece.position;

import java.util.Arrays;

public enum Rank {
    One(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8)
    ;

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    private static Rank valueOf(int value) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.value == value)
            .findFirst()
            .get();
    }

    public int getValue() {
        return value;
    }

    public Rank getUp() {
        if (this == Eight) {
            return this;
        }

        return Rank.valueOf(value + 1);
    }

    public Rank getDown() {
        if (this == One) {
            return this;
        }

        return Rank.valueOf(value - 1);
    }

    public Rank getNext(int next) {
        if (value + next < One.value || value + next > Eight.value) {
            return this;
        }

        return Rank.valueOf(value + next);
    }
}
