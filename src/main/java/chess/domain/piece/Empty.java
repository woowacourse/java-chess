package chess.domain.piece;

import chess.domain.position.Position;

public class Empty extends Piece {

    private static final String INVALID_PIECE_MESSAGE = "움직일 수 없습니다.";

    private Empty(Color color) {
        super(color, RoleType.EMPTY);
    }

    public static Empty create() {
        return new Empty(Color.EMPTY);
    }

    @Override
    protected boolean validMove(Position sourcePosition, Position targetPosition, Color targetColor) {
        throw new UnsupportedOperationException(INVALID_PIECE_MESSAGE);
    }
}
