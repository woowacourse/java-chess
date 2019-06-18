package chess.domain;

public class King implements Piece {
    private static final String NAME = "K";
    private static final double SCORE = 0;

    private final Aliance aliance;

    public King(Aliance aliance) {
        this.aliance = aliance;
    }

    @Override
    public void move() {

    }

    @Override
    public String toString() {
        if (aliance == Aliance.BLACK) {
            return NAME;
        }
        return NAME.toLowerCase();
    }
}
