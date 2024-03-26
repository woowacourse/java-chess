package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.route.Route;

public class Rook extends Piece {

    public Rook(Side side) {
        super(side);
    }

    @Override
    boolean hasFollowedRule(Position source, Position target, Route route) {
        return source.isSameFile(target) || source.isSameRank(target);
    }
}
