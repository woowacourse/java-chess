package chess.util;

import chess.domain.board.Square;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;

public class TestPiece extends Piece {
    public TestPiece(final Color color) {
        super(color);
    }

    @Override
    public List<Square> findRoute(final Square source, final Square destination) {
        return null;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
