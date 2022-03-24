package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.KnightMovableStrategy;

public final class Knight extends Piece {

    private static final String KNIGHT_NAME = "N";

    public Knight(Color color) {
        super(color, KNIGHT_NAME, new KnightMovableStrategy());
    }
}
