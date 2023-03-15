package chess.piece;

public class Rook extends Piece {
    public Rook(Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }
}
