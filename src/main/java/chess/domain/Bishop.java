package chess.domain;

public class Bishop implements Piece {
    private static final String NAME = "B";
    private static final double SCORE = 3;

    private final Aliance aliance;
    private Position position;

    public Bishop(Aliance aliance, Position position) {
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

