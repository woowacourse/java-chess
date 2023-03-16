package domain;

import domain.type.SpecialRule;
import java.util.List;
import java.util.stream.IntStream;

public class PathValidator {

    public boolean validateNormal(final Square start, final List<Square> paths) {
        if (isBlocked(paths)) {
            return false;
        }
        final Square end = paths.get(paths.size() - 1);
        //폰일때 아무도 없는 곳으로 대각선을 가는 경우에 대한 처리가 필요
        return start.haveDifferentColor(end);
    }

    public boolean validateSpecial(final SpecialValidateDto start, final SpecialValidateDto end) {
        return SpecialRule.containRuleBy(start, end);
    }

    private boolean isBlocked(final List<Square> paths) {
        return IntStream.range(0, paths.size() - 1)
            .mapToObj(paths::get)
            .anyMatch(Square::isNotEmpty);
    }
}
