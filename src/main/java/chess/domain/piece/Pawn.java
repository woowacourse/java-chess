package chess.domain.piece;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    public static Pawn of(final Color color) {
        return new Pawn(color);
    }
}
