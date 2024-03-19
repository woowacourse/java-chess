package chess.domain.attribute;

public enum File {
    A, B, C, D, E, F, G, H;

    public static File of(final int index) {
        return values()[index];
    }
}
