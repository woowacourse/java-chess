package chess.piece;

public class King extends Piece {

    public King(Color color) {
        super(Type.KING, color);
    }

    @Override
    boolean isMovable(int x, int y, int toX, int toY) {
        return false;
    }
}
