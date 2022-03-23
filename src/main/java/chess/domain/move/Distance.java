package chess.domain.move;

public class Distance {

    private final int vertical;
    private final int horizon;

    public Distance(final int vertical, final int horizon) {
        this.vertical = vertical;
        this.horizon = horizon;
    }

    public int getVertical() {
        return vertical;
    }

    public int getHorizon() {
        return horizon;
    }
}
