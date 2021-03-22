package chess.domain.piece;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Notation;
import chess.domain.piece.strategy.PawnMoveStrategy;

public class Pawn extends Piece {
    private static final Notation PAWN_NOTATION = new Notation("P");

    public Pawn(Color color) {
        super(color, PAWN_NOTATION, new PawnMoveStrategy(color));
    }
}
