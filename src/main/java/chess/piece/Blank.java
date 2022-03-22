package chess.piece;

public class Blank extends Piece {

    public Blank() {
        super(Type.BLANK, Color.NONE);
    }

    @Override
    boolean isMovable(int x, int y, int toX, int toY) {
        return false;
    }
}
