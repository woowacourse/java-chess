package chess.domain.piece;

public abstract class Piece {
    protected Position position;
    protected String name;

    public Piece(Position position, String name) {
        this.position = position;
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    abstract void move(Position position);
}
