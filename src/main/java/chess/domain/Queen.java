package chess.domain;

public class Queen implements Piece {
    private static final String NAME = "Q";
    private static final double SCORE = 9;

    private final Aliance aliance;
    private Position position;

    public Queen(Aliance aliance, Position position) {
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

