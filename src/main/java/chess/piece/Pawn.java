package chess.piece;

public class Pawn extends Piece {
    public Pawn(Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }
}
