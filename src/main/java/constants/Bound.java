package constants;

public enum Bound {
    BOARD_LOWER_BOUND(0),
    BOARD_UPPER_BOUND(7);

    private final int value;

    Bound(final int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
