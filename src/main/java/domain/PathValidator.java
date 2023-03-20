package domain;

import domain.piece.Piece;
import domain.type.SpecialRule;
import java.util.List;
import java.util.stream.IntStream;

public class PathValidator {

    public boolean isValid(final Section start, final Section end, final List<Piece> path) {
        if (isBlocked(path)) {
            return false;
        }
        final SpecialRule specialRule = SpecialRule.getRuleBy(start, end);
        if (!specialRule.equals(SpecialRule.NOT_EXIST)) {
            return specialRule.judge(start, end);
        }
        return start.getPiece().isDifferentColor(end.getPiece());
    }

    private boolean isBlocked(final List<Piece> paths) {
        return IntStream.range(0, paths.size() - 1)
            .mapToObj(paths::get)
            .anyMatch(Piece::isNotEmpty);
    }
}
