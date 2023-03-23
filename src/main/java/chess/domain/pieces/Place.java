package chess.domain.pieces;

import chess.domain.board.Position;
import chess.exception.PieceMessage;

public class Place extends Piece {

    private static final String PLACE = ".";

    public Place() {
        super(new Name(PLACE));
    }

    @Override
    public void canMove(final Position start, final Position end) {
        throw new IllegalArgumentException(PieceMessage.PLACE_INVALID_MOVE.getMessage());
    }
}
