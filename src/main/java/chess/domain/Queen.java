package chess.domain;

public class Queen implements Piece {
    private static final String NAME = "Q";
    private static final double SCORE = 9;

    private final Aliance aliance;

    public Queen(Aliance aliance) {
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

