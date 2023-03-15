package chess.piece;

public class King extends Piece {
    public King(Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }
}
