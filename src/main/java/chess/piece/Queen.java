package chess.piece;

public class Queen extends Piece {

    public Queen(final Team team) {
        super(team);
    }

    @Override
    public void move() {

    }

    @Override
    public boolean isQueen() {
        return true;
    }
}
