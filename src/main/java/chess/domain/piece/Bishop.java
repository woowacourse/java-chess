package chess.domain.piece;

import chess.domain.piece.strategy.BishopStrategy;

public class Bishop extends Piece {

    public Bishop(PieceColor pieceColor) {
        super(PieceType.BISHOP, pieceColor, new BishopStrategy());
    }
}
