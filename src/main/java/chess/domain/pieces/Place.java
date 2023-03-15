package chess.domain.pieces;

public class Place extends Piece {

    public Place() {
        super(new Name("."));
    }

    @Override
    public void canMove(final String start, final String end) {
        throw new IllegalArgumentException("움직일 수 없는 말입니다.");
    }
}
