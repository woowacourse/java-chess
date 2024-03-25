package chess.domain.piece;

import chess.domain.route.Route;
import chess.domain.position.Position;

public class Bishop extends Piece {

    public Bishop(Side side) {
        super(side);
    }

    @Override
    boolean hasFollowedRule(Position source, Position target, Route route) {
        return source.isDiagonal(target);
    }
}
