package chess.piece;

public class Knight extends Piece {

    public Knight(Color color) {
        super(Type.KNIGHT, color);
    }

    @Override
    boolean isMovable(int x, int y, int toX, int toY) {
        return false;
    }
}