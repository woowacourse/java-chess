package chess.domain.piece;

import chess.domain.square.Square;

public class King extends Piece {

    private static final int STEP_LIMIT = 1;

    public King(PieceColor color, Square square) {
        super(color, square);
    }

    @Override
    public void move(Square target) {

    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }
}
