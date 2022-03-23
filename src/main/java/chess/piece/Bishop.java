package chess.piece;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
