package chess.domain.piece;

import chess.domain.MovingRange;
import chess.domain.board.Tile;

public class King extends Piece {
    King(PieceColor color, PieceType type) {
        super(color, type);
    }

    @Override
    protected MovingRange getRange(boolean isTargetEmpty, Tile current) {
        return MovingRange.KING_RANGE;
    }
}
