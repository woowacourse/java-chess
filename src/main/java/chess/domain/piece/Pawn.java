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
    public boolean canMove(Direction direction, Piece target) {
        if (color == Color.BLACK) {
            direction = direction.flipAboutX();
        }
        checkSameTeam(target);
        List<Direction> directions = new ArrayList<>(MOVABLE_DIRECTIONS);
        checkCanAttack(direction, target, directions);
        checkIsStart(directions);
        return direction.hasSame(directions);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    private void checkCanAttack(Direction direction, Piece target, List<Direction> directions) {
        if (direction.hasSame(ATTACK_DIRECTIONS) && !target.isNone()) {
            directions.addAll(ATTACK_DIRECTIONS);
        }
    }

    private void checkIsStart(List<Direction> directions) {
        if (start) {
            this.start = false;
            directions.add(new Direction(0, 2));
        }
    }
}
