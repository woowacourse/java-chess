package chess.domain.piece.info;

public class Score {
    private final double value;

    public Score(double value) {
        validateNegative(value);
        this.value = value;
    }

    private void validateNegative(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("[ERROR] 음수는 올 수 없습니다.");
        }
    }

    public double getValue() {
        return value;
    }
}
