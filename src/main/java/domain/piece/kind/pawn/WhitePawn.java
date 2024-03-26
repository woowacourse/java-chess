package domain.piece.kind.pawn;

import static domain.piece.attribute.point.Movement.LEFT_UP;
import static domain.piece.attribute.point.Movement.RIGHT_UP;
import static domain.piece.attribute.point.Movement.UP;
import static domain.piece.attribute.point.Movement.UP_UP;

import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Movement;
import domain.piece.attribute.point.Point;
import java.util.HashSet;
import java.util.Set;

public class WhitePawn extends Pawn {
    private static final Set<Movement> attackMovements = Set.of(RIGHT_UP, LEFT_UP);
    private static final Movement DOUBLE_STEP = UP_UP;
    private static final Movement NORMAL_STEP = UP;

    protected WhitePawn(Point point, Color color) {
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
