package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

import chess.domain.position.Direction;

public final class Pawn extends Piece {
    private final static String BUG_MESSAGE_COLOR = "[BUG] 폰은 색상을 가져야합니다.";
    private static final String BLACK_PAWN = "♟";
    private static final String WHITE_PAWN = "♙";
    private static final List<Direction> MOVABLE_DIRECTIONS = new ArrayList<>();
    private static final List<Direction> ATTACK_DIRECTIONS = List.of(new Direction(1, 1), new Direction(-1, 1));
    private static final List<Direction> START_DIRECTIONS = List.of(new Direction(0, 2));
    private static final String ERROR_MESSAGE_ATTACK_DIRECTION = "[ERROR] 5252! 폰은 대각선으로만 공격할 수 있다능~";

    static {
        MOVABLE_DIRECTIONS.add(new Direction(0, 1));
    }

    private boolean start;

    Pawn(Color color) {
        super(color);
        this.start = true;
        this.score = 1;
    }

    @Override
    public String getEmoji() {
        if (color == Color.NONE) {
            throw new IllegalArgumentException(BUG_MESSAGE_COLOR);
        }

        if (color == Color.BLACK) {
            return BLACK_PAWN;
        }

        return WHITE_PAWN;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean canMove(Direction direction, Piece target) {
        if (color == Color.BLACK) {
            direction = direction.flipAboutX();
        }

        if (checkCanAttack(direction, target) || checkIsStart(direction, target)
                || checkCanMove(direction, target)) {
            this.start = false;
            return true;
        }
        return false;
    }

    private boolean checkCanAttack(Direction direction, Piece target) {
        checkSameTeam(target);
        return direction.hasSame(ATTACK_DIRECTIONS) && !target.isNone();
    }

    private boolean checkIsStart(Direction direction, Piece target) {
        checkTarget(target);
        return start && direction.hasSame(START_DIRECTIONS);
    }

    private boolean checkCanMove(Direction direction, Piece target) {
        checkTarget(target);
        return direction.hasSame(MOVABLE_DIRECTIONS);
    }

    private void checkTarget(Piece target) {
        checkSameTeam(target);
        if (!target.isNone()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ATTACK_DIRECTION);
        }
    }
}
