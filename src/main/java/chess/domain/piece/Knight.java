package chess.domain.piece;

import chess.domain.board.MoveType;
import chess.domain.position.Move;

public class Knight extends Piece {

    private static final int PRODUCT_OF_CHANGE = 2;

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Move move, MoveType moveType) {
        return move.isRightProductOfChange(PRODUCT_OF_CHANGE);
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
