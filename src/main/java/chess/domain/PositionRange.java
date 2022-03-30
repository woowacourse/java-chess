package chess.domain;

public enum PositionRange {

    COLUMN_RANGE('a', 'h'),
    ROW_RANGE('1', '8'),
    ;

    private final char allowedMinimum;
    private final char allowedMaximum;

    PositionRange(final char allowedMinimum, final char allowedMaximum) {
        this.allowedMinimum = allowedMinimum;
        this.allowedMaximum = allowedMaximum;
    }

    public boolean isOutOfRange(final char target) {
        return (target < allowedMinimum || allowedMaximum < target);
    }

    public char getAllowedMinimum() {
        return allowedMinimum;
    }

    public char getAllowedMaximum() {
        return allowedMaximum;
    }
}
