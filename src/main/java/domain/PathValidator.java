package domain;

import domain.type.SpecialRule;
import java.util.List;
import java.util.stream.IntStream;

public class PathValidator {

    public boolean isValid(final ValidateDto start, final ValidateDto end,
        final List<Square> path) {
        if (isBlocked(path)) {
            return false;
        }
        SpecialRule specialRule = SpecialRule.getRuleBy(start, end);
        if (!specialRule.equals(SpecialRule.NOT_EXIST)) {
            return specialRule.judge(start, end);
        }
        return start.getPiece().isDifferentColor(end.getPiece());
    }

    private boolean isBlocked(final List<Square> paths) {
        return IntStream.range(0, paths.size() - 1)
            .mapToObj(paths::get)
            .anyMatch(Square::isNotEmpty);
    }
}
