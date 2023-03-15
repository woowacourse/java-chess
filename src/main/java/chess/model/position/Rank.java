package chess.model.position;

public enum Rank {

    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5),
    SIXTH(6),
    SEVENTH(7),
    EIGHTH(8);

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public int differ(final Rank other) {
        return this.value - other.value;
    }
}
