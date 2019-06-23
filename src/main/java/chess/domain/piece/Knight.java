package chess.domain.piece;

import chess.domain.Range;
import chess.domain.board.Tile;

public class Knight extends Piece{
    public Knight(PieceColor color) {
        super(color);
    }

    @Override
    protected Range getRange(boolean isTargetEmpty, Tile current) {
        return Range.KNIGHT_RANGE;
    }
}
