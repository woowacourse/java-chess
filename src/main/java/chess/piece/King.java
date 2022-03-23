package chess.piece;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }
}
