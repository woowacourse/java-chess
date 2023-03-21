package domain.piece;

import domain.Location;
import domain.Section;
import domain.type.Direction;
import domain.type.PieceType;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public enum PawnRule {

    PAWN_ATTACK(PawnRule::isPawnAttackCondition, PawnRule::judgePawnAttack),
    PAWN_MOVE_ONCE(PawnRule::isPawnMoveOnce, PawnRule::judgePawnMoveOnce),
    PAWN_MOVE_TWICE(PawnRule::isPawnMoveTwice, PawnRule::judgePawnMoveTwice),
    NOT_EXIST((start, end) -> false, (start, end) -> false);

    private final BiPredicate<Section, Section> condition;
    private final BiPredicate<Section, Section> judge;

    PawnRule(final BiPredicate<Section, Section> condition, final BiPredicate<Section, Section> judge) {
        this.condition = condition;
        this.judge = judge;
    }

    private static boolean isPawnMoveOnce(final Section start, final Section end) {
        if (!start.getPiece().isSameType(PieceType.PAWN)) {
            return false;
        }
        final Direction direction = Direction.find(start.getLocation(), end.getLocation());
        final Location expected = start.getLocation().addDirectionOnce(direction);
        final List<Direction> moveDirection = List.of(Direction.UP, Direction.DOWN);
        return moveDirection.contains(direction) && expected.equals(end.getLocation());
    }

    private static boolean judgePawnMoveOnce(final Section start, final Section end) {
        final Piece piece = start.getPiece();
        final Direction direction = Direction.find(start.getLocation(), end.getLocation());
        if (piece.isWhite()) {
            return direction.equals(Direction.UP) && end.getPiece().isSameType(PieceType.EMPTY);
        }
        return direction.equals(Direction.DOWN) && end.getPiece().isSameType(PieceType.EMPTY);
    }

    private static boolean isPawnMoveTwice(final Section start, final Section end) {
        if (!start.getPiece().isSameType(PieceType.PAWN)) {
            return false;
        }
        final Direction direction = Direction.find(start.getLocation(), end.getLocation());
        final Location expected = start.getLocation()
            .addDirectionOnce(direction)
            .addDirectionOnce(direction);
        final List<Direction> moveDirection = List.of(Direction.UP, Direction.DOWN);
        return moveDirection.contains(direction) && expected.equals(end.getLocation());
    }

    private static boolean judgePawnMoveTwice(final Section start, final Section end) {
        final Piece piece = start.getPiece();
        final Direction direction = Direction.find(start.getLocation(), end.getLocation());
        if (piece.isWhite()) {
            return judgeWhitePawnMoveTwice(start, end, direction);
        }
        return judgeBlackPawnMoveTwice(start, end, direction);
    }

    private static boolean judgeWhitePawnMoveTwice(final Section start, final Section end,
        final Direction direction) {
        return start.getRow() == 2
            && direction.equals(Direction.UP)
            && end.getPiece().isSameType(PieceType.EMPTY);
    }

    private static boolean judgeBlackPawnMoveTwice(final Section start, final Section end,
        final Direction direction) {
        return start.getRow() == 7
            && direction.equals(Direction.DOWN)
            && end.getPiece().isSameType(PieceType.EMPTY);
    }

    private static boolean isPawnAttackCondition(final Section start, final Section end) {
        final Piece startPiece = start.getPiece();
        return startPiece.isSameType(PieceType.PAWN)
            && start.isDiagonal(end);
    }

    private static boolean judgePawnAttack(final Section start, final Section end) {
        final Piece piece = start.getPiece();
        if (piece.isWhite()) {
            return judgeWhitePawnAttack(start, end);
        }
        return judgeBlackPawnAttack(start, end);
    }

    private static boolean judgeWhitePawnAttack(Section start, Section end) {
        final Direction direction = Direction.find(start.getLocation(), end.getLocation());
        final List<Direction> attackDirection = List.of(Direction.LEFT_UP, Direction.RIGHT_UP);
        final Location expected = start.getLocation().addDirectionOnce(direction);
        return attackDirection.contains(direction)
            && expected.equals(end.getLocation())
            && start.getPiece().isEnemy(end.getPiece());
    }

    private static boolean judgeBlackPawnAttack(Section start, Section end) {
        final Direction direction = Direction.find(start.getLocation(), end.getLocation());
        final List<Direction> attackDirection = List.of(Direction.LEFT_DOWN, Direction.RIGHT_DOWN);
        final Location expected = start.getLocation().addDirectionOnce(direction);
        return attackDirection.contains(direction)
            && expected.equals(end.getLocation())
            && start.getPiece().isEnemy(end.getPiece());
    }

    public static PawnRule find(final Section start, final Section end) {
        return Arrays.stream(PawnRule.values())
            .filter(rule -> rule.condition.test(start, end))
            .findAny()
            .orElse(NOT_EXIST);
    }

    public boolean judge(final Section start, final Section end) {
        return judge.test(start, end);
    }
}
