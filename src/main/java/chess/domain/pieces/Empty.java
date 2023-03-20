package chess.domain.pieces;

public class Empty extends Piece {

    public Empty(Team team) {
        super(team);
    }

    @Override
    public void canMove(final String start, final String end) {
        throw new IllegalArgumentException("움직일 수 없는 말입니다.");
    }
}
