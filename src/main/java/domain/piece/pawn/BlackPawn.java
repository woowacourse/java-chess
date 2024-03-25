package domain.piece.pawn;

import domain.piece.Color;
import domain.position.Position;
import domain.position.Rank;

public class BlackPawn extends Pawn {
    @Override
    protected boolean isMovedBack(Position source, Position target) {
        return source.isLowerRankThan(target);
    }

    @Override
    protected Rank initialRank() {
        return Rank.SEVEN;
    }

    @Override
    public Color color() {
        return Color.BLACK;
    }
}
