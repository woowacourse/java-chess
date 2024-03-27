package domain.position;

import java.util.Objects;

public class Rank {

    private final int number;

    public Rank(final int number) {
        this.number = number;
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
