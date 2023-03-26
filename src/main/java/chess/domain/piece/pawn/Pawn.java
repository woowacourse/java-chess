package chess.domain.piece.pawn;

import chess.domain.game.Position;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class Pawn extends Piece {

    public static final double DEGRADED_SCORE = 0.5;
    private static final String INVALID_MOVEMENT_MESSAGE = "폰이 해당 지점으로 이동할 수 없습니다.";

    private final AttackStrategies attackStrategies;

    protected Pawn(final Team team, final PieceType pieceType, final MovingStrategies movingStrategies, final AttackStrategies attackStrategies) {
        super(team, pieceType, movingStrategies);
        this.attackStrategies = attackStrategies;
    }

    @Override
    public List<Position> calculatePath(final MovingStrategy strategy, final Position source, final Position target, final Team targetTeam) {
        if (attackStrategies.contains(strategy)) {
            return attack(strategy, source, target, targetTeam);
        }
        return getPath(source, target, targetTeam);
    }

    private List<Position> attack(final MovingStrategy strategy, final Position source, final Position target, final Team targetTeam) {
        final Position movedPosition = strategy.move(source);
        if (movedPosition.equals(target) && team.isOpponent(targetTeam)) {
            return Collections.emptyList();
        }
        throw new IllegalArgumentException(INVALID_MOVEMENT_MESSAGE);
    }

    private List<Position> getPath(final Position source, final Position target, final Team targetTeam) {
        final MovingStrategy movingStrategy = movingStrategies.findStrategy(source, target);
        final Position movedPosition = movingStrategy.move(source);
        if (movedPosition.equals(target) && targetTeam.isEmpty()) {
            return Collections.emptyList();
        }
        throw new IllegalArgumentException(INVALID_MOVEMENT_MESSAGE);
    }

    protected static final class AttackStrategies {

        private final List<MovingStrategy> strategies;

        public AttackStrategies(final List<MovingStrategy> strategies) {
            this.strategies = strategies;
        }

        public Optional<MovingStrategy> findStrategy(final Position source, final Position target) {
            return strategies.stream()
                    .filter(strategy -> strategy.movable(source, target))
                    .findFirst();
        }

        private boolean contains(final MovingStrategy strategy) {
            return strategies.contains(strategy);
        }
    }
}
