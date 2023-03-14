package chess.domain;

public class Side {
    private final Color color;

    public Side(final Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color.name();
    }
}
