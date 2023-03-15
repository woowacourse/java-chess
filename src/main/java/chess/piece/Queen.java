package chess.piece;

public class Queen extends Piece {
    public Queen(Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.QUEEN;
    }
}
