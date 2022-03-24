package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.RookMovableStrategy;

public final class Rook extends Piece {

    private static final String ROOK_NAME = "R";

    public Rook(Color color) {
        super(color, ROOK_NAME, new RookMovableStrategy());
    }
}
