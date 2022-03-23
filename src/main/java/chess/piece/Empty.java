package chess.piece;

public class Empty extends Piece {

    public Empty(Color color) {
        super(color);
    }

    @Override
    public PieceType getType() {
        return PieceType.EMPTY;
    }
}
