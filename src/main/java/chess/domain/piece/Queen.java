package chess.domain.piece;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Notation;
import chess.domain.piece.strategy.QueenMoveStrategy;

public class Queen extends RealPiece {
    private static final Notation QUEEN_NOTATION = new Notation("Q");

    public Queen(Color color) {
        super(color, QUEEN_NOTATION, new QueenMoveStrategy());
    }
}
