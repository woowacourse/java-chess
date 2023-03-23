package chess.domain.pieces;

import chess.domain.board.Position;
import chess.exception.PieceMessage;

public class Place extends Piece {

    private static final String PLACE = ".";
    private static final double SCORE_OF_PLACE = 0;

    public Place() {
        super(new Name(PLACE));
        this.score = new Score(SCORE_OF_PLACE);
    }

    @Override
    public void canMove(final Position start, final Position end) {
        throw new IllegalArgumentException(PieceMessage.PLACE_INVALID_MOVE.getMessage());
    }

    @Override
    public boolean isPlace() {
        return this.name.isPlace();
    }
}
