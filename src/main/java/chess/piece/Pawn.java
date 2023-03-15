package chess.piece;

public class Pawn extends Piece {

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public void move() {

    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
