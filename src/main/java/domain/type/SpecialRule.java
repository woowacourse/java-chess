package domain.type;

import domain.Location;
import domain.ValidateDto;
import domain.piece.Piece;
import domain.type.direction.PieceMoveDirection;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public enum SpecialRule {

    PAWN_ATTACK(SpecialRule::isPawnAttackCondition, SpecialRule::judgePawnAttack),
    PAWN_MOVE_ONCE(SpecialRule::isPawnMoveOnce, SpecialRule::judgePawnMoveOnce),
    PAWN_MOVE_TWICE(SpecialRule::isPawnMoveTwice, SpecialRule::judgePawnMoveTwice),
    NOT_EXIST((start, end) -> false, (start, end) -> false);

    private static final int WHITE_PAWN_INITIAL_ROW = 1;
    private static final int BLACK_PAWN_INITIAL_ROW = 6;
    private final BiPredicate<ValidateDto, ValidateDto> condition;
    private final BiPredicate<ValidateDto, ValidateDto> judge;

    SpecialRule(final BiPredicate<ValidateDto, ValidateDto> condition,
        final BiPredicate<ValidateDto, ValidateDto> judge) {
        this.condition = condition;
        this.judge = judge;
    }

    private static boolean isPawnAttackCondition(final ValidateDto start, final ValidateDto end) {
        final Piece startPiece = start.getPiece();
        return startPiece.isSameType(PieceType.PAWN)
            && start.getLocation().isDiagonal(end.getLocation());
    }

    private static boolean judgePawnAttack(final ValidateDto start, final ValidateDto end) {
        final Piece piece = start.getPiece();
        if (piece.isWhite()) {
            return judgeWhitePawnAttack(start, end);
        }
        return judgeBlackPawnAttack(start, end);
    }

    private static boolean judgeWhitePawnAttack(final ValidateDto start, final ValidateDto end) {
        final PieceMoveDirection direction = PieceMoveDirection.find(start.getLocation(), end.getLocation());
        final List<PieceMoveDirection> attackDirection = List.of(PieceMoveDirection.LEFT_UP,
            PieceMoveDirection.RIGHT_UP);
        final Location expected = start.getLocation().addDirectionOnce(direction);
        return attackDirection.contains(direction)
            && expected.equals(end.getLocation())
            && start.getPiece().isEnemy(end.getPiece());
    }

    private static boolean judgeBlackPawnAttack(final ValidateDto start, final ValidateDto end) {
        final PieceMoveDirection direction = PieceMoveDirection.find(start.getLocation(), end.getLocation());
        final List<PieceMoveDirection> attackDirection = List.of(PieceMoveDirection.LEFT_DOWN,
            PieceMoveDirection.RIGHT_DOWN);
        final Location expected = start.getLocation().addDirectionOnce(direction);
        return attackDirection.contains(direction)
            && expected.equals(end.getLocation())
            && start.getPiece().isEnemy(end.getPiece());
    }

    private static boolean isPawnMoveOnce(final ValidateDto start, final ValidateDto end) {
        if (!start.getPiece().isSameType(PieceType.PAWN)) {
            return false;
        }
        final PieceMoveDirection direction = PieceMoveDirection.find(start.getLocation(), end.getLocation());
        final Location expected = start.getLocation().addDirectionOnce(direction);
        final List<PieceMoveDirection> moveDirection = List.of(PieceMoveDirection.UP, PieceMoveDirection.DOWN);
        return moveDirection.contains(direction) && expected.equals(end.getLocation());
    }

    private static boolean judgePawnMoveOnce(final ValidateDto start, final ValidateDto end) {
        final Piece piece = start.getPiece();
        final PieceMoveDirection direction = PieceMoveDirection.find(start.getLocation(), end.getLocation());
        if (piece.isWhite()) {
            return direction.equals(PieceMoveDirection.UP) && end.getPiece().isSameType(PieceType.EMPTY);
        }
        return direction.equals(PieceMoveDirection.DOWN) && end.getPiece().isSameType(PieceType.EMPTY);
    }

    private static boolean isPawnMoveTwice(final ValidateDto start, final ValidateDto end) {
        if (!start.getPiece().isSameType(PieceType.PAWN)) {
            return false;
        }
        final PieceMoveDirection direction = PieceMoveDirection.find(start.getLocation(), end.getLocation());
        final Location expected = start.getLocation()
            .addDirectionOnce(direction)
            .addDirectionOnce(direction);
        final List<PieceMoveDirection> moveDirection = List.of(PieceMoveDirection.UP, PieceMoveDirection.DOWN);
        return moveDirection.contains(direction) && expected.equals(end.getLocation());
    }

    private static boolean judgePawnMoveTwice(final ValidateDto start, final ValidateDto end) {
        final Piece piece = start.getPiece();
        final PieceMoveDirection direction = PieceMoveDirection.find(start.getLocation(), end.getLocation());
        if (piece.isWhite()) {
            return judgeWhitePawnMoveTwice(start, end, direction);
        }
        return judgeBlackPawnMoveTwice(start, end, direction);
    }

    private static boolean judgeWhitePawnMoveTwice(final ValidateDto start, final ValidateDto end,
        final PieceMoveDirection direction) {
        return start.getLocation().getRow() == WHITE_PAWN_INITIAL_ROW && direction.equals(PieceMoveDirection.UP)
            && end.getPiece()
            .isSameType(PieceType.EMPTY);
    }

    private static boolean judgeBlackPawnMoveTwice(final ValidateDto start, final ValidateDto end,
        final PieceMoveDirection direction) {
        return start.getLocation().getRow() == BLACK_PAWN_INITIAL_ROW && direction.equals(PieceMoveDirection.DOWN)
            && end.getPiece()
            .isSameType(PieceType.EMPTY);
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
