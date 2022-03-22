package chess.domain.square;

public abstract class Piece implements Square {

    protected final Color color;

    protected Piece(Color color) {
        this.color = color;
    }
}
