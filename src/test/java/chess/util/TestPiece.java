package chess.util;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class TestPiece extends Piece {
    public TestPiece(final Color color) {
        super(color, new TestStrategy());
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
