package chess.piece;

public class Empty extends Piece {

    public Empty(Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.EMPTY;
    }
}
