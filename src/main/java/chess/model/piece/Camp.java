package chess.model.piece;

public enum Camp {

    BLACK,
    WHITE,
    EMPTY;

    public boolean isSameCamp(final Camp other) {
        return this == other;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }
}
