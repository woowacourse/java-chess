package chess.domain.piece;

import chess.domain.piece.strategy.KingStrategy;

public class Knight extends Piece {

    public Knight(PieceColor pieceColor) {
        super(PieceType.KNIGHT, pieceColor, new KingStrategy());
    }
}
