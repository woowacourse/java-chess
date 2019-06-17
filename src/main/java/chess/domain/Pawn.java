package chess.domain;

public class Pawn implements Piece {
    private static final String NAME = "P";
    private static final double SCORE = 1;

    private final Aliance aliance;
    private Position position;

    public Pawn(Aliance aliance, Position position) {
        this.aliance = aliance;
        this.position = position;
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

