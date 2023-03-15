package chess.piece;

public class Knight extends Piece {
    public Knight(Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }
}
