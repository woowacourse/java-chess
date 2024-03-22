package domain.piece.pawn;

import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import domain.position.Rank;

public class WhitePawn extends PawnPiece {
    private static final Rank INITIAL_RANK = Rank.TWO;

    public WhitePawn() {
        super(Color.WHITE);
    }

    @Override
    public void validate(final Position source, final Position target, final Piece other) {
        if (source.isAtSameRank(INITIAL_RANK) && source.isStraightAt(target) &&
                source.isLowerRankThan(target) && source.isDistanceAt(target, 2) && isEmpty(other)) {
            return;
        }
        if (source.isStraightAt(target) && source.isLowerRankThan(target) &&
                source.isDistanceAt(target, 1) && isEmpty(other)) {
            return;
        }
        if (source.isDiagonalAt(target) && source.isAdjacentAt(target) &&
                source.isLowerRankThan(target) && isOppositeColor(other)) {
            return;
        }
        throw new IllegalArgumentException("잘못된 방향으로 이동하고 있습니다.");
    }

    private boolean isEmpty(Piece other) {
        return other.color().isNeutrality();
    }

    private boolean isOppositeColor(Piece other) {
        if (isEmpty(other)) {
            return false;
        }
        return this.color() != other.color();
    }

    @Override
    public Color color() {
        return Color.WHITE;
    }
}
