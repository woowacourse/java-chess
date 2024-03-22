package chess.model.position;

import static java.lang.Math.abs;

public class Difference {
    private static final int MAX_AMOUNT = Rank.length() - 1;

    private final int amount;

    public Difference(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (abs(amount) > MAX_AMOUNT) {
            throw new IllegalArgumentException("File 혹은 Rank의 좌표차 절댓값은 " + MAX_AMOUNT + " 이하입니다.");
        }
    }

    public boolean isZero() {
        return amount == 0;
    }

    public boolean isNegative() {
        return amount < 0;
    }

    public boolean isPositive() {
        return amount > 0;
    }

    public int plusByAbsoluteValue(Difference other) {
        return this.absoluteValue() + other.absoluteValue();
    }

    public int absoluteValue() {
        return abs(amount);
    }
}
