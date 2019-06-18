package chess.domain;

public class Knight implements Piece {
    private static final String NAME = "N";
    private static final double SCORE = 2.5;

    private final Aliance aliance;

    public Knight(Aliance aliance) {
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

