package chess.piece;

public class Rook extends Piece {

    public Rook(Color color) {
        super(Type.ROOK, color);
    }

    @Override
    boolean isMovable(int x, int y, int toX, int toY) {
        return false;
    }
}
