package chess.domain.piece;

import chess.domain.Range;
import chess.domain.board.Tile;

public class King extends Piece{
    public King(PieceColor color) {
        super(color);
    }

    @Override
    protected Range getRange(boolean isTargetEmpty, Tile current) {
        return Range.KING_RANGE;
    }
}
