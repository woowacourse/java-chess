package chess.model.piece;

public enum Camp {

    BLACK,
    WHITE;

    public boolean isSameCamp(final Camp other) {
        return this == other;
    }
}
