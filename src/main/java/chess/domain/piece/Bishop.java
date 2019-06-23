package chess.domain.piece;

import chess.domain.Range;
import chess.domain.board.Tile;

public class Bishop extends Piece{
    public Bishop(PieceColor color) {
        super(color);
    }

    @Override
    protected Range getRange(boolean isTargetEmpty, Tile current) {
        return Range.BISHOP_RANGE;
    }
}
