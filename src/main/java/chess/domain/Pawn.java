package chess.domain;

public class Pawn implements Piece {
    private static final String NAME = "P";
    private static final double SCORE = 1;

    private final Aliance aliance;

    public Pawn(Aliance aliance) {
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

