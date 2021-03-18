package chess.domain.piece;

import chess.domain.piece.strategy.QueenStrategy;

public class Queen extends Piece {

    public Queen(PieceColor pieceColor) {
        super(PieceType.QUEEN, pieceColor, new QueenStrategy());
    }
}
