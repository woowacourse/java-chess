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

    public final List<Position> findPath(Position start, Position end, boolean hasEnemy) {
        return movementRules.stream()
                .filter(movementRule -> movementRule.isMovable(start, end, hasEnemy))
                .findAny()
                .map(movementRule -> movementRule.findPath(start, end, hasEnemy))
                .orElseThrow(() -> new IllegalArgumentException("불가능한 경로입니다."));
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public boolean isPawn() {
        return PieceType.from(this) == PieceType.PAWN;
    }

    public boolean isKing() {
        return PieceType.from(this) == PieceType.KING;
    }
}
