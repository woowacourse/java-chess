package chess.domain.board;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int rankNumber;

    Rank(int rankNumber) {
        this.rankNumber = rankNumber;
    }

    public static Rank from(int number) {
        return Arrays.stream(values())
                .filter(rank -> rank.rankNumber == number)
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public static Rank from(String input) {
        return from(Integer.parseInt(input.trim()));
    }

    public int dy(Rank another) {
        return another.rankNumber - this.rankNumber;
    }
}
