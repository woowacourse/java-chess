package domain.piece;

import static domain.position.Direction.DOWN;
import static domain.position.Direction.LEFT_DOWN;
import static domain.position.Direction.LEFT_UP;
import static domain.position.Direction.RIGHT_DOWN;
import static domain.position.Direction.RIGHT_UP;
import static domain.position.Direction.UP;

import domain.position.Direction;
import domain.position.Position;
import domain.position.Rank;
import java.util.Set;

public class Pawn extends Piece {

    private static final Direction VALID_DIRECTIONS_WHITE_MOVE = UP;
    private static final Direction VALID_DIRECTIONS_BLACK_MOVE = DOWN;
    private static final Set<Direction> VALID_DIRECTIONS_WHITE_ATTACK = Set.of(RIGHT_UP, LEFT_UP);
    private static final Set<Direction> VALID_DIRECTIONS_BLACK_ATTACK = Set.of(RIGHT_DOWN, LEFT_DOWN);
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
        Direction direction = Direction.asDirection(source, target);
        if (isFirstMove(source)) {
            return VALID_DIRECTIONS_BLACK_MOVE == direction && source.isLegalRankStep(target, ONE_STEP, TWO_STEP);
        }
        return VALID_DIRECTIONS_BLACK_MOVE == direction && source.isLegalRankStep(target, ONE_STEP);
    }

    private boolean canMoveWhenWhite(Position source, Position target) {
        Direction direction = Direction.asDirection(source, target);
        if (isFirstMove(source)) {
            return VALID_DIRECTIONS_WHITE_MOVE == direction && source.isLegalRankStep(target, ONE_STEP, TWO_STEP);
        }
        return VALID_DIRECTIONS_WHITE_MOVE == direction && source.isLegalRankStep(target, ONE_STEP);
    }

    private boolean isFirstMove(Position source) {
        if (isBlack()) {
            return source.hasRank(INIT_RANK_OF_BLACK);
        }
        return source.hasRank(INIT_RANK_OF_WHITE);
    }

    @Override
    public boolean canAttack(Position source, Position target) {

        Direction direction = Direction.asDirection(source, target);
        if (isBlack()) {
            return VALID_DIRECTIONS_BLACK_ATTACK.contains(direction) && distanceOneRankOneFile(source, target);
        }
        return VALID_DIRECTIONS_WHITE_ATTACK.contains(direction) && distanceOneRankOneFile(source, target);
    }

    private boolean distanceOneRankOneFile(Position source, Position target) {
        return source.isLegalRankStep(target, ONE_STEP)
                && source.isLegalFileStep(target, ONE_STEP);
    }
}
