package chess.domain.piece;

import chess.domain.MovingRange;
import chess.domain.board.Tile;

public class Pawn extends Piece {
    Pawn(PieceColor color, PieceType type) {
        super(color, type);
    }

    @Override
    protected MovingRange getRange(boolean haveTarget, Tile current) {
        return PawnType.getRange(getColor(), current, haveTarget);
    }
}


