package chess.domain.piece;

public class Bishop extends Piece {
    
    private Bishop(final Color color) {
        super(color, PieceType.BISHOP);
    }
    
    public static Bishop create(final Color color) {
        return new Bishop(color);
    }
}
