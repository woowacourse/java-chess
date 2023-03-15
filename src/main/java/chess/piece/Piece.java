package chess.piece;

public abstract class Piece {

    private final Team team;

    protected Piece(final Team team) {
        this.team = team;
    }

    public abstract void move();
}
