package chess.domain.piece;

import chess.domain.square.Square;

public class Rook extends Piece {

    public Rook(PieceColor color, Square square) {
        super(color, square);
    }

    @Override
    public void move(Square target) {

    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }
}
