package chess.domain.piece;

import chess.domain.movement.MovementRule;
import chess.domain.position.Position;
import java.util.List;

public final class Pawn extends Piece {

    public Pawn(Team team) {
        super(team, findMovementRule(team));
    }

    private static List<MovementRule> findMovementRule(Team team) {
        if (team.isBlack()) {
            return List.of();
        }
        return List.of();
    }

    // TODO
    @Override
    public List<Position> findPath(Position start, Position end) {
        return super.findPath(start, end);
    }
}
