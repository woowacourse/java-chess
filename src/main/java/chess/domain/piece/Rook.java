package chess.domain.piece;

import chess.domain.piece.strategy.RookStrategy;

public class Rook extends Piece {

    public Rook(PieceColor pieceColor) {
        super(PieceType.ROOK, pieceColor, new RookStrategy());
    }
}
