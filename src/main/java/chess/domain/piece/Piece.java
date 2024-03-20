package chess.domain.piece;

import chess.domain.movement.MovementRule;
import chess.domain.position.Position;
import java.util.List;

public abstract class Piece {

    private final Team team;
    private final List<MovementRule> movementRules;

    protected Piece(Team team, List<MovementRule> movementRules) {
        this.team = team;
        this.movementRules = movementRules;
    }

    public final boolean isBlackTeam() {
        return team.isBlack();
    }

    public final List<Position> findPath(Position start, Position end) {
        return movementRules.stream()
                .filter(movementRule -> movementRule.isMovable(start, end))
                .findAny()
                .map(movementRule -> movementRule.findPath(start, end))
                .orElseThrow(() -> new IllegalArgumentException("불가능한 경로입니다."));
    }
}
