package chess.domain.piece;

import chess.domain.MovingRange;
import chess.domain.board.Tile;

public class King extends Piece {
    King(PieceColor color) {
        super(color);
    }

    @Override
    protected MovingRange getRange(boolean isTargetEmpty, Tile current) {
        return MovingRange.KING_RANGE;
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double getScore() {
        return 0;
    }

    @Override
    public String getType() {
        return PieceGenerator.KING.toString();
    }
}
