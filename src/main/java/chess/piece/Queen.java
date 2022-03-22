package chess.piece;

public class Queen extends Piece {

    public Queen(Color color) {
        super(Type.QUEEN, color);
    }

    @Override
    boolean isMovable(int x, int y, int toX, int toY) {
        return false;
    }
}
