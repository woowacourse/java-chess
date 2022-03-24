package chess.piece;

import chess.board.Square;

public final class None extends Piece {
    private static final String NONE = "·";

    None (Color color) {
        super(color);
    }

    @Override
    public String getEmoji() {
        return NONE;
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return false;
    }
}
