package chess.piece;

public class King extends Piece {

    public King(final Team team) {
        super(team);
    }

    @Override
    public void move() {

    }

    @Override
    public boolean isKing() {
        return true;
    }
}
