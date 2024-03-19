package chess.domain.piece;

import chess.domain.Square;

public class EmptyPiece extends Piece{

    public EmptyPiece(Square square) {
        super(Color.NONE, square);
    }
}
