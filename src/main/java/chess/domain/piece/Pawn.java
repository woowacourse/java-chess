package chess.domain.piece;

import chess.domain.piece.strategy.PawnStrategy;

public abstract class Pawn extends Piece {

    public Pawn(PieceColor pieceColor) {
        super(PieceType.PAWN, pieceColor, new PawnStrategy());
    }

}
