package domain;

public abstract class Piece {

    private final Color color;
    private final InitialColumns initialPositions;

    public Piece(Color color, InitialColumns initialPositions) {
        this.color = color;
        this.initialPositions = initialPositions;
    }
}
