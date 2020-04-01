package chess.domain.board;

public interface Location {
    int getValue();

    default boolean inBetween(final Location start, final Location end) {
        if (start.getValue() >= end.getValue()) {
            return this.getValue() >= end.getValue() && this.getValue() <= start.getValue();
        }
        return this.getValue() <= end.getValue() && this.getValue() >= start.getValue();
    }
}
