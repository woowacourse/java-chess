package chess.domain;

public abstract class Piece {
    private Position position;
    private Color color;

    public Piece(Position position) {
        this.position = position;
    }

    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }
}
