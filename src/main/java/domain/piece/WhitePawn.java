package domain.piece;

import domain.position.Position;
import domain.position.Rank;

public class WhitePawn extends Pawn {
    @Override
    boolean isMovedBack(Position source, Position target) {
        return source.isUpperRankThan(target);
    }

    @Override
    Rank initialRank() {
        return Rank.TWO;
    }

    @Override
    public Color color() {
        return Color.WHITE;
    }
}
