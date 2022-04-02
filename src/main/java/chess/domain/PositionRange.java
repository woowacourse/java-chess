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

    public boolean isOutOfRange(final int target) {
        return isOutOfRange((char) target);
    }

    public boolean isEndOfRange(final char row) {
        return row == ROW_RANGE.allowedMinimum || row == ROW_RANGE.allowedMaximum;
    }

    public char getAllowedMinimum() {
        return allowedMinimum;
    }

    public char getAllowedMaximum() {
        return allowedMaximum;
    }
}
