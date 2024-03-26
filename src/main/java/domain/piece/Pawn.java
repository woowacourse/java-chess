package domain.piece;

import static domain.position.Movement.DOWN;
import static domain.position.Movement.LEFT_DOWN;
import static domain.position.Movement.LEFT_UP;
import static domain.position.Movement.RIGHT_DOWN;
import static domain.position.Movement.RIGHT_UP;
import static domain.position.Movement.UP;

import domain.position.Movement;
import domain.position.Position;
import domain.position.Rank;
import java.util.Set;

public class Pawn extends Piece {

    private static final Movement VALID_MOVEMENTS_WHITE_MOVE = UP;
    private static final Movement VALID_MOVEMENTS_BLACK_MOVE = DOWN;
    private static final Set<Movement> VALID_MOVEMENTS_WHITE_ATTACK = Set.of(RIGHT_UP, LEFT_UP);
    private static final Set<Movement> VALID_MOVEMENTS_BLACK_ATTACK = Set.of(RIGHT_DOWN, LEFT_DOWN);
    private static final Rank INIT_RANK_OF_WHITE = Rank.TWO;
    private static final Rank INIT_RANK_OF_BLACK = Rank.SEVEN;
    private static final int ONE_STEP = 1;
    private static final int TWO_STEP = 2;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        if (isBlack()) {
            return canMoveWhenBlack(source, target);
        }
        return canMoveWhenWhite(source, target);
    }

    private boolean canMoveWhenBlack(Position source, Position target) {
        Movement movement = Movement.asMovement(source, target);
        if (isFirstMove(source)) {
            return VALID_MOVEMENTS_BLACK_MOVE == movement && source.isLegalRankStep(target, ONE_STEP, TWO_STEP);
        }
        return VALID_MOVEMENTS_BLACK_MOVE == movement && source.isLegalRankStep(target, ONE_STEP);
    }

    private boolean canMoveWhenWhite(Position source, Position target) {
        Movement movement = Movement.asMovement(source, target);
        if (isFirstMove(source)) {
            return VALID_MOVEMENTS_WHITE_MOVE == movement && source.isLegalRankStep(target, ONE_STEP, TWO_STEP);
        }
        return VALID_MOVEMENTS_WHITE_MOVE == movement && source.isLegalRankStep(target, ONE_STEP);
    }

    private boolean isFirstMove(Position source) {
        if (isBlack()) {
            return source.hasRank(INIT_RANK_OF_BLACK);
        }
        return source.hasRank(INIT_RANK_OF_WHITE);
    }

    @Override
    public boolean canAttack(Position source, Position target) {

        Movement movement = Movement.asMovement(source, target);
        if (isBlack()) {
            return VALID_MOVEMENTS_BLACK_ATTACK.contains(movement) && distanceOneRankOneFile(source, target);
        }
        return VALID_MOVEMENTS_WHITE_ATTACK.contains(movement) && distanceOneRankOneFile(source, target);
    }

    private boolean distanceOneRankOneFile(Position source, Position target) {
        return source.isLegalRankStep(target, ONE_STEP)
                && source.isLegalFileStep(target, ONE_STEP);
    }
}
