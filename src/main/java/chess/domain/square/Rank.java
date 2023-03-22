package chess.domain.square;

import java.util.Arrays;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int position;

    Rank(final int position) {
        this.position = position;
    }

    public static Rank from(final int value) {
        return Arrays.stream(values())
                .filter(rank -> rank.position == value)
                .findFirst()
                .orElseThrow(
                        () -> {
                            throw new IllegalArgumentException("해당 값의 랭크가 없습니다.");
                        }
                );
    }

    public int calculateDifference(final Rank target) {
        return target.position - this.position;
    }

    public Rank getNextRank(final int rankDifference) {
        int nextPosition = this.position + rankDifference;
        return Rank.from(nextPosition);
    }

    @Override
    public String toString() {
        return Integer.toString(position);
    }
}
