package chess.domain.piece;

public class Rook extends Piece {
    
    private Rook(final Color color) {
        super(color);
    }
    
    public static Rook create(final Color color) {
        return new Rook(color);
    }
}
