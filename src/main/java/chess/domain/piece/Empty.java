package chess.domain.piece;

public class Empty extends Piece {
    public Empty() {
        super(Team.EMPTY, Role.EMPTY);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
