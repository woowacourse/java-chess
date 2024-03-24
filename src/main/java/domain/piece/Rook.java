package domain.piece;

import domain.position.Position;
import domain.Side;

import java.util.Map;

public class Rook extends Piece {
    public Rook(Side side) {
        super(side);
    }

    @Override
    public boolean isRook() {
        return true;
    }

    @Override
    public boolean canMove(Position current, Position target, Map<Position, Piece> pieces) {
        checkBlockingPiece(target, pieces);
        return current.isSameRank(target) || current.isSameFile(target);
    }
}
