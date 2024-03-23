package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.square.Square;

public class Knight extends Piece {

    private static final int SIDE_STEP = 1;
    private static final int STRAIGHT_STEP = 2;

    public Knight(PieceColor color, Square square) {
        super(color, square);
    }

    @Override
    public void move(Board board, Square target) {

    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
