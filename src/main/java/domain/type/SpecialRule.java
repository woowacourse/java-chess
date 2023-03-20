package domain.type;

import domain.Location;
import domain.ValidateDto;
import domain.piece.Piece;
import domain.piece.PieceType;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public enum SpecialRule {

    PAWN_ATTACK(SpecialRule::isPawnAttackCondition, SpecialRule::judgePawnAttack),
    PAWN_MOVE_ONCE(SpecialRule::isPawnMoveOnce, SpecialRule::judgePawnMoveOnce),
    PAWN_MOVE_TWICE(SpecialRule::isPawnMoveTwice, SpecialRule::judgePawnMoveTwice),
    NOT_EXIST((start, end) -> false, (start, end) -> false);

    private final BiPredicate<ValidateDto, ValidateDto> condition;
    private final BiPredicate<ValidateDto, ValidateDto> judge;

    SpecialRule(BiPredicate<ValidateDto, ValidateDto> condition,
        BiPredicate<ValidateDto, ValidateDto> judge) {
        this.condition = condition;
        this.judge = judge;
    }

    private static boolean isPawnMoveOnce(final ValidateDto start, final ValidateDto end) {
        if (!start.getPiece().isSameType(PieceType.PAWN)) {
            return false;
        }
        final Direction direction = Direction.find(start.getLocation(), end.getLocation());
        final Location expected = start.getLocation().addDirectionOnce(direction);
        final List<Direction> moveDirection = List.of(Direction.UP, Direction.DOWN);
        return moveDirection.contains(direction) && expected.equals(end.getLocation());
    }

    private static boolean judgePawnMoveOnce(final ValidateDto start, final ValidateDto end) {
        final Piece piece = start.getPiece();
        final Direction direction = Direction.find(start.getLocation(), end.getLocation());
        if (piece.isWhite()) {
            return direction.equals(Direction.UP) && end.getPiece().isSameType(PieceType.EMPTY);
        }
        return direction.equals(Direction.DOWN) && end.getPiece().isSameType(PieceType.EMPTY);
    }

    private static boolean isPawnMoveTwice(final ValidateDto start, final ValidateDto end) {
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

    private static boolean judgePawnMoveTwice(final ValidateDto start, final ValidateDto end) {
        Piece piece = start.getPiece();
        Direction direction = Direction.find(start.getLocation(), end.getLocation());
        if (piece.isWhite()) {
            return judgeWhitePawnMoveTwice(start, end, direction);
        }
        return judgeBlackPawnMoveTwice(start, end, direction);
    }

    private static boolean judgeWhitePawnMoveTwice(final ValidateDto start, final ValidateDto end,
        final Direction direction) {
        return start.getLocation().getRow() == 2
            && direction.equals(Direction.UP)
            && end.getPiece().isSameType(PieceType.EMPTY);
    }

    private static boolean judgeBlackPawnMoveTwice(final ValidateDto start, final ValidateDto end,
        final Direction direction) {
        return start.getLocation().getRow() == 7
            && direction.equals(Direction.DOWN)
            && end.getPiece().isSameType(PieceType.EMPTY);
    }

    private static boolean isPawnAttackCondition(final ValidateDto start, final ValidateDto end) {
        Piece startPiece = start.getPiece();
        return startPiece.isSameType(PieceType.PAWN)
            && start.getLocation().isDiagonal(end.getLocation());
    }

    private static boolean judgePawnAttack(final ValidateDto start, final ValidateDto end) {
        Piece piece = start.getPiece();
        if (piece.isWhite()) {
            return judgeWhitePawnAttack(start, end);
        }
        return judgeBlackPawnAttack(start, end);
    }

    private static boolean judgeWhitePawnAttack(ValidateDto start, ValidateDto end) {
        final Direction direction = Direction.find(start.getLocation(), end.getLocation());
        final List<Direction> attackDirection = List.of(Direction.LEFT_UP, Direction.RIGHT_UP);
        final Location expected = start.getLocation().addDirectionOnce(direction);
        return attackDirection.contains(direction)
            && expected.equals(end.getLocation())
            && start.getPiece().isEnemy(end.getPiece());
    }

    private static boolean judgeBlackPawnAttack(ValidateDto start, ValidateDto end) {
        final Direction direction = Direction.find(start.getLocation(), end.getLocation());
        final List<Direction> attackDirection = List.of(Direction.LEFT_DOWN, Direction.RIGHT_DOWN);
        final Location expected = start.getLocation().addDirectionOnce(direction);
        return attackDirection.contains(direction)
            && expected.equals(end.getLocation())
            && start.getPiece().isEnemy(end.getPiece());
    }

    public static SpecialRule getRuleBy(final ValidateDto start, final ValidateDto end) {
        return Arrays.stream(SpecialRule.values())
            .filter(rule -> rule.condition.test(start, end))
            .findAny()
            .orElse(NOT_EXIST);
    }

    public boolean judge(final ValidateDto start, final ValidateDto end) {
        return judge.test(start, end);
    }
}
