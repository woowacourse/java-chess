package chess.domain;

public class Knight implements Piece {
    private static final String NAME = "N";
    private static final double SCORE = 2.5;

    private final Aliance aliance;
    private Position position;

    public Knight(Aliance aliance, Position position) {
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

