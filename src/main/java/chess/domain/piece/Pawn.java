package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.BlackPawnDefaultMovement;
import chess.domain.movement.BlackPawnDiagonalMovement;
import chess.domain.movement.BlackPawnFirstMovement;
import chess.domain.movement.MovementRule;
import chess.domain.movement.WhitePawnDefaultMovement;
import chess.domain.movement.WhitePawnDiagonalMovement;
import chess.domain.movement.WhitePawnFirstMovement;
import java.util.List;

public final class Pawn extends Piece {

    private static final List<MovementRule> BLACK_MOVEMENT_RULES = List.of(
            new BlackPawnFirstMovement(), new BlackPawnDefaultMovement(), new BlackPawnDiagonalMovement());
    private static final List<MovementRule> WHITE_MOVEMENT_RULES = List.of(
            new WhitePawnFirstMovement(), new WhitePawnDefaultMovement(), new WhitePawnDiagonalMovement());

    public Pawn(Team team) {
        super(team, findMovementRule(team));
    }

    private static List<MovementRule> findMovementRule(Team team) {
        if (team.isBlack()) {
            return BLACK_MOVEMENT_RULES;
        }
        return WHITE_MOVEMENT_RULES;
    }
}
