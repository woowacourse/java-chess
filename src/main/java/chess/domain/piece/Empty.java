package chess.domain.piece;

import chess.domain.board.Position;

public class Empty extends Piece {

    private static final String INVALID_PIECE_MESSAGE = "움직일 수 없습니다.";

    private Empty(Color color) {
        super(color);
    }

    public static Piece create() {
        return new Empty(Color.EMPTY);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Color color) {
        throw new UnsupportedOperationException(INVALID_PIECE_MESSAGE);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Piece move() {
        throw new UnsupportedOperationException(INVALID_PIECE_MESSAGE);
    }
}
