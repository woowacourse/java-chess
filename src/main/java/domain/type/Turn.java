package domain.type;

public enum Turn {

    WHITE, BLACK;

    public Turn convert() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }
}
