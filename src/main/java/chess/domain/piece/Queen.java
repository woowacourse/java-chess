package chess.domain.piece;

import chess.domain.Range;
import chess.domain.board.Tile;

public class Queen extends Piece{
    Queen(PieceColor color) {
        super(color);
    }

    @Override
    protected Range getRange(boolean isTargetEmpty, Tile current) {
        return Range.QUEEN_RANGE;
    }
}
