package chess.domain.piece;

public class Queen extends Piece {
    
    private Queen(final Color color) {
        super(color, PieceType.QUEEN);
    }
    
    public static Queen create(final Color color) {
        return new Queen(color);
    }
}
