package chess.domain.piece;

public class Knight extends Piece {
    
    private Knight(final Color color) {
        super(color);
    }
    
    public static Knight create(final Color color) {
        return new Knight(color);
    }
}
