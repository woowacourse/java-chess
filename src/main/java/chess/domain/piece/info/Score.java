package chess.domain.piece.info;

public class Score {
    private static final String NEGATIVE_ERROR = "[ERROR] 음수는 올 수 없습니다.";
    private final double value;

    public Score(double value) {
        validateNegative(value);
        this.value = value;
    }

    private void validateNegative(double value) {
        if (value < 0) {
            throw new IllegalArgumentException(NEGATIVE_ERROR);
        }
    }

    public double getValue() {
        return value;
    }
}
