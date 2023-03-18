package chess.domain.piece;

import chess.domain.piece.strategy.QueenStrategy;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color, new QueenStrategy());
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
