package chess.domain.piece;

import chess.domain.MovingRange;
import chess.domain.board.Tile;

public class Rook extends Piece {
    Rook(PieceColor color) {
        super(color);
    }

    @Override
    protected MovingRange getRange(boolean isTargetEmpty, Tile current) {
        return MovingRange.ROOK_RANGE;
    }

    @Override
    public double getScore() {
        return 5;
    }

    @Override
    public String getType() {
        return PieceGenerator.ROOK.toString();
    }
}
