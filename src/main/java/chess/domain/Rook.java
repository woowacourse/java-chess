package chess.domain;

public class Rook implements Piece {
    private static final String NAME = "R";
    private static final double SCORE = 5;

    private final Aliance aliance;
    private Position position;

    public Rook(Aliance aliance, Position position) {
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

