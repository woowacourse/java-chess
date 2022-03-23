package chess.piece;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
