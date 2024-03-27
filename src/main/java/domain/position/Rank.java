package domain.position;

import java.util.Objects;

public class Rank {
    public static final int START_NUMBER = 1;
    public static final int END_NUMBER = 8;
    private final int number;

    public Rank(final int number) {
        validate(number);
        this.number = number;
    }

    private void validate(final int number) {
        if (isInvalidNumber(number)) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 Rank 숫자 입력입니다.");
        }
    }

    private boolean isInvalidNumber(final int number) {
        return number < START_NUMBER || number > END_NUMBER;
    }

    public int subtract(final Rank target) {
        return number - target.number;
    }

    public Rank add(final int movement) {
        return new Rank(number + movement);
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
