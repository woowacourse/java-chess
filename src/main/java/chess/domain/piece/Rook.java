package chess.domain.piece;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }
}
