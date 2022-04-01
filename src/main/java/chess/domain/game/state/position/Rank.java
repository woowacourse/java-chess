package chess.domain.game.state.position;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Rank {
    One(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Out(-1)
    ;

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank of(int value) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.value == value)
            .findFirst()
            .orElseThrow(NoSuchElementException::new);
    }

    public int getValue() {
        return value;
    }

    public Rank findNext(int next) {
        if (value + next < One.value || value + next > Eight.value) {
            return Out;
        }

        return Rank.of(value + next);
    }
}
