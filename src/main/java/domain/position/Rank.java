package domain.position;

import java.util.Objects;

public class Rank {

    private final int number;

    public Rank(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
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

    public int subtract(Rank target) {
        return number - target.getNumber();
    }
}
