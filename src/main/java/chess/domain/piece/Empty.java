package chess.domain.piece;

public class Empty extends Piece {

    private Empty() {
        super(Color.EMPTY, PieceType.EMPTY);
    }

    public static Empty create() {
        return new Empty();
    }
}
