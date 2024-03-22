package chess.domain.piece;

public enum PieceColor {
    WHITE(PieceNamePattern.WHITE),
    BLACK(PieceNamePattern.BLACK);

    private final PieceNamePattern namePattern;

    PieceColor(PieceNamePattern namePattern) {
        this.namePattern = namePattern;
    }

    public String applyNamePattern(final String name) {
        return namePattern.apply(name);
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}
