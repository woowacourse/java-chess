package domain;

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
        return start.isSameType(PieceType.PAWN)
            && start.getLocation().isDiagonal(end.getLocation())
            && end.getPiece() != null
            && start.getPiece().isDifferentColor(end.getPiece());
    }

    public static boolean containRuleBy(final SpecialValidateDto start, final SpecialValidateDto end) {
        return Arrays.stream(SpecialRule.values())
            .anyMatch(rule -> rule.condition.test(start, end));
    }
}
