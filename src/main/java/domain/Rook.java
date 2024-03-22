package domain;

import domain.piece.Piece;
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
    public boolean isRuleBroken(Position current, Position target, Map<Position, Piece> pieces) {
        checkBlockingPiece(target, pieces);
        return current.isSameRank(target) || current.isSameFile(target);
    }
}
