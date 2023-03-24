package chess.domain.piece;

import chess.domain.position.Movement;
import chess.domain.position.Position;

public class Empty extends Piece {

    private static final Empty INSTANCE = new Empty(Color.EMPTY);
    private static final String EMPTY_PIECE_CANT_SEARCH_MOVEMENT = "이 Piece는 움직일 수 있는 기능이 없습니다.";

    private Empty(final Color color) {
        super(color, PieceType.EMPTY);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    public static Empty getInstance() {
        return INSTANCE;
    }

    @Override
    public Movement searchMovement(final Position from, final Position to, final Piece destination) {
        throw new UnsupportedOperationException(EMPTY_PIECE_CANT_SEARCH_MOVEMENT);
    }
}
