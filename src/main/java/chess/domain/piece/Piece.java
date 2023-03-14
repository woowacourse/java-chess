package chess.domain.piece;

public class Piece {

    private final Staunton staunton;
    private final Color color;

    public Piece(final Staunton staunton, final Color color) {
        this.staunton = staunton;
        this.color = color;
    }

    public Staunton staunton() {
        return staunton;
    }

    public Color color() {
        return color;
    }
}
