package domain.type;

import domain.SpecialValidateDto;
import domain.piece.Piece;
import domain.piece.PieceType;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum SpecialRule {

    PAWN_ATTACK(SpecialRule::isPawnAttack);

    private final BiPredicate<SpecialValidateDto, SpecialValidateDto> condition;

    SpecialRule(final BiPredicate<SpecialValidateDto, SpecialValidateDto> condition) {
        this.condition = condition;
    }

    private static boolean isPawnAttack(final SpecialValidateDto start, final SpecialValidateDto end) {
        Piece startPiece = start.getPiece();
        Piece endPiece = end.getPiece();
        return startPiece.isSameType(PieceType.PAWN)
            && startPiece.isEnemy(endPiece)
            && start.getLocation().isDiagonal(end.getLocation());
    }

    public static boolean containRuleBy(final SpecialValidateDto start, final SpecialValidateDto end) {
        return Arrays.stream(SpecialRule.values())
            .anyMatch(rule -> rule.condition.test(start, end));
    }
}
