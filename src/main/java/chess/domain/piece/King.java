package chess.domain.piece;

public class King extends Piece {
    
    private King(final Color color) {
        super(color, PieceType.KING);
    }
    
    public static King create(final Color color) {
        return new King(color);
    }
}
