package chess;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        return false;
    }
}
