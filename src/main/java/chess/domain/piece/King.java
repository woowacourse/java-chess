package chess.domain.piece;

import chess.domain.route.Route;
import chess.domain.square.Square;

public class King extends Piece {

    public King(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Square source, Square target, Route route) {
        return (source.isSameFile(target) || source.hasOneFileGap(target)) &&
                (source.isSameRank(target) || source.hasOneRankGap(target));
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KING;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
