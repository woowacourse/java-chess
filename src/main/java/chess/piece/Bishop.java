package chess.piece;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(Type.BISHOP, color);
    }

    @Override
    boolean isMovable(int x, int y, int toX, int toY) {
        return false;
    }
}
