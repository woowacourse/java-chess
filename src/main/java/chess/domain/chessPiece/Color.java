package chess.domain.chessPiece;

public enum Color {
    WHITE,
    BLACK;

    public boolean isBlack() {
        return this.equals(BLACK);
    }

    public String convertByColor(String name) {
        if (isBlack()) {
            return name.toUpperCase();
        }
        return name.toLowerCase();
    }
}
