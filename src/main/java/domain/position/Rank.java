package domain.position;

import java.util.Objects;

public class Rank {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 8;

    private final int number;

    public Rank(int number) {
        validateNumberRange(number);
        this.number = number;
    }

    private void validateNumberRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException();
        }
    }

    public Rank(char number) {
        this(Character.getNumericValue(number));
    }

    public int subtract(Rank target) {
        return number - target.number;
    }

    public Rank add(final int movement) {
        return new Rank(number + movement);
    }

    public boolean isRank2() {
        return number == 2;
    }

    public boolean isRank7() {
        return number == 7;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rank rank = (Rank) o;
        return number == rank.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
