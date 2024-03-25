package chess.domain.piece;

import chess.domain.route.Route;
import chess.domain.position.Position;

public class Knight extends Piece {

    public Knight(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Position source, Position target, Route route) {
        return source.hasTwoFileGap(target) && source.hasOneRankGap(target) ||
                source.hasOneFileGap(target) && source.hasTwoRankGap(target);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KNIGHT;
    }

    @Override
    public boolean isKnight() {
        return true;
    }
}
