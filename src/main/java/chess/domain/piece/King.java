package chess.domain.piece;

import chess.domain.piece.strategy.KingStrategy;

public class King extends Piece {

    public King(PieceColor pieceColor) {
        super(PieceType.KING, pieceColor, new KingStrategy());
    }
}
