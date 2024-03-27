package chessgame.domain.piece.kind.pawn;

import static chessgame.domain.piece.attribute.point.Movement.DOWN;
import static chessgame.domain.piece.attribute.point.Movement.DOWN_DOWN;
import static chessgame.domain.piece.attribute.point.Movement.LEFT_DOWN;
import static chessgame.domain.piece.attribute.point.Movement.RIGHT_DOWN;

import chessgame.domain.piece.Piece;
import chessgame.domain.piece.Pieces;
import chessgame.domain.piece.attribute.Color;
import chessgame.domain.piece.attribute.point.Movement;
import chessgame.domain.piece.attribute.point.Point;
import chessgame.domain.piece.attribute.point.Rank;
import java.util.HashSet;
import java.util.Set;

public class BlackPawn extends Pawn {
    private static final Set<Movement> attackMovements = Set.of(RIGHT_DOWN, LEFT_DOWN);
    private static final Movement DOUBLE_STEP = DOWN_DOWN;
    private static final Movement NORMAL_STEP = DOWN;
    private static final Rank NEVER_MOVE_RANK = Rank.SEVEN;

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

    @Override
    protected Piece update(Point point) {
        return new BlackPawn(point, color);
    }

    private void insertAbleToAttack(Pieces pieces, HashSet<Movement> availableMovement) {
        attackMovements.stream()
                .filter(movement ->  point.canMove(movement))
                .filter(movement -> hasEnemy(pieces, movement))
                .forEach(availableMovement::add);
    }

    private void insertSpecialCase(Pieces pieces, HashSet<Movement> availableMovement) {
        if (point.rank() == NEVER_MOVE_RANK && pieces.findPieceWithPoint(point.move(DOUBLE_STEP)).isEmpty()) {
            availableMovement.add(DOUBLE_STEP);
        }
    }
}
