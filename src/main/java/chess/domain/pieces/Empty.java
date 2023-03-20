package chess.domain.pieces;

public class Empty extends Piece {

    private static final String PLACE = ".";

    public Empty() {
        super(new Name(PLACE));
    }

    @Override
    public void canMove(final String start, final String end) {
        throw new IllegalArgumentException("움직일 수 없는 말입니다.");
    }
}
