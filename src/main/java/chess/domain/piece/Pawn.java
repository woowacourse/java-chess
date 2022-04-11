package chess.domain.piece;

import chess.domain.piece.movestrategy.PawnMoveStrategy;

public final class Pawn extends Piece {

    public Pawn(final Color color) {
        super(Notation.PAWN, color, new PawnMoveStrategy(color));
    }
}
