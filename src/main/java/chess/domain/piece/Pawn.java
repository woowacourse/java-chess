package chess.domain.piece;

import chess.domain.MovingRange;
import chess.domain.board.Tile;

public class Pawn extends Piece {
    Pawn(PieceColor color) {
        super(color);
    }

    @Override
    protected MovingRange getRange(boolean haveTarget, Tile current) {
        return PawnType.getRange(getColor(), current, haveTarget);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double getScore() {
        return 1;
    }

    @Override
    public String getType() {
        return PieceGenerator.PAWN.toString();
    }
}