package domain.piece;

import domain.position.Position;
import domain.position.Rank;

public class BlackPawn extends Pawn {
    @Override
    boolean isMovedBack(Position source, Position target) {
        return source.isLowerRankThan(target);
    }

    @Override
    Rank initialRank() {
        return Rank.SEVEN;
    }

    @Override
    public Color color() {
        return Color.BLACK;
    }
}
