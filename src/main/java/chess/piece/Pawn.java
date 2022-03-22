package chess.piece;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(Type.PAWN, color);
    }

    @Override
    boolean isMovable(int x, int y, int toX, int toY) {
        return false;
    }
}