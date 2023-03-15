package chess.domain.piece;

public class Empty extends Piece {
    
    private Empty() {
        super(Color.NONE, PieceType.EMPTY);
    }
    
    public static Empty create() {
        return new Empty();
    }
}
