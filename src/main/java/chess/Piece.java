package chess;

public class Piece {
    private final String type;
    private final Position position;

    public Piece(String type, Position position) {
        this.type = type;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
