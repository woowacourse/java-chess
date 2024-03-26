package domain.piece.kind.pawn;

import static domain.piece.attribute.point.Movement.DOWN;
import static domain.piece.attribute.point.Movement.DOWN_DOWN;
import static domain.piece.attribute.point.Movement.LEFT_DOWN;
import static domain.piece.attribute.point.Movement.RIGHT_DOWN;

import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Movement;
import domain.piece.attribute.point.Point;
import java.util.HashSet;
import java.util.Set;

public class BlackPawn extends Pawn {
    private static final Set<Movement> attackMovements = Set.of(RIGHT_DOWN, LEFT_DOWN);
    private static final Movement DOUBLE_STEP = DOWN_DOWN;
    private static final Movement NORMAL_STEP = DOWN;

    protected BlackPawn(Point point, Color color) {
        super(point, color);
    }

    @Override
    protected Set<Movement> getMovableDirection(Pieces pieces) {
        final var availableMovement = new HashSet<Movement>();

        insertAbleToAttack(pieces, availableMovement);

        if (pieces.findPieceWithPoint(point.move(NORMAL_STEP)).isPresent()) {
            return availableMovement;
        }

        insertSpecialCase(pieces, availableMovement);
        availableMovement.add(NORMAL_STEP);
        return availableMovement;
    }

    private void insertAbleToAttack(Pieces pieces, HashSet<Movement> availableMovement) {
        attackMovements.stream()
                .filter(movement -> hasEnemy(pieces, movement))
                .forEach(availableMovement::add);
    }

    private void insertSpecialCase(Pieces pieces, HashSet<Movement> availableMovement) {
        if (!moved && pieces.findPieceWithPoint(point.move(DOUBLE_STEP)).isEmpty()) {
            availableMovement.add(DOUBLE_STEP);
        }
    }
}
