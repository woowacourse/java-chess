package chess.domain.piece;

import chess.domain.square.Square;
import chess.domain.route.Route;

public class Rook extends Piece {

    public Rook(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Square source, Square target, Route route) {
        return source.isSameFile(target) || source.isSameRank(target);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.ROOK;
    }

    @Override
    public boolean isRook() {
        return true;
    }
}
