package chess.domain.piece;

import chess.domain.route.Route;
import chess.domain.position.Position;

public class King extends Piece {

    public King(Side side) {
        super(side);
    }

    @Override
    boolean hasFollowedRule(Position source, Position target, Route route) {
        return (source.isSameFile(target) || source.hasOneFileGap(target)) &&
                (source.isSameRank(target) || source.hasOneRankGap(target));
    }
}
