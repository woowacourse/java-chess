package chess.domain.piece;

import chess.domain.square.Square;

public class Bishop extends Piece {
    public Bishop(PieceColor color, Square square) {
        super(color, square);
    }

    @Override
    public void move(Square target) {

    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
