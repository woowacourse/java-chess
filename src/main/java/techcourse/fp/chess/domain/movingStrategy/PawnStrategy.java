package techcourse.fp.chess.domain.movingStrategy;

import java.util.List;
import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.Directions;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.Rank;

public class PawnStrategy {

    private final Directions attackDirections;
    private final Direction moveDirection;

    private PawnStrategy(final Directions attackDirections, final Direction moveDirection) {
        this.attackDirections = attackDirections;
        this.moveDirection = moveDirection;
    }

    public static PawnStrategy ofWhite() {
        return new PawnStrategy(new Directions(Direction.ofWhitePawnAttack()), Direction.ofWhitePawnMove());
    }

    public static PawnStrategy ofBlack() {
        return new PawnStrategy(new Directions(Direction.ofBlackPawnAttack()), Direction.ofBlackPawnMove());
    }

    public boolean isAttackable(Position source, Position target) {
        return attackDirections.hasMovableDirection(source, target);
    }

    public boolean isOneStepDistance(Position source, Position target) {
        final int file = target.getGapOfFileOrder(source);
        final int rank = target.getGapOfRankOrder(source);
        return moveDirection.isSame(file, rank);
    }

    public boolean isTwoStepDistance(Position source, Position target) {
        return target.getRankOrder() == source.getRankOrder() + moveDirection.getRank() * 2;
    }

    public List<Position> createPath(Position source) {
        return List.of(Position.of(source.getFile(), Rank.of(source.getRankOrder() + moveDirection.getRank())));
    }
}
