package chess.domain.pieces;

import chess.domain.board.Position;

public class Place extends Piece {

    private static final String PLACE = ".";

    public Place() {
        super(new Name(PLACE));
    }

    @Override
    public void canMove(final Position start, final Position end) {
        throw new IllegalArgumentException("움직일 수 없는 말입니다.");
    }
}
