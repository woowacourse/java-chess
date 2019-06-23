package chess.domain.piece;

import chess.domain.Range;
import chess.domain.board.Tile;

public class Rook extends Piece {
    public Rook(PieceColor color) {
        super(color);
    }

    @Override
    protected Range getRange(boolean isTargetEmpty, Tile current) {
        return Range.ROOK_RANGE;
    }

}
