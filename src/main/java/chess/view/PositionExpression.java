package chess.view;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PositionExpression {

    private static final Pattern POSITION_PATTERN = Pattern.compile("^[a-h][1-8]$");
    private static final Map<String, Position> CACHE = new HashMap<>();

    private PositionExpression() {
    }

    public static Position mapToPosition(final String rawInput) {
        validatePositionFormat(rawInput);

        return CACHE.computeIfAbsent(rawInput, PositionExpression::makePosition);
    }

    private static void validatePositionFormat(final String rawInput) {
        Matcher matcher = POSITION_PATTERN.matcher(rawInput);
        boolean matches = matcher.matches();

        if (!matches) {
            throw new IllegalArgumentException("올바르지 않은 형식의 위치 입력입니다.");
        }
    }

    private static Position makePosition(String rawInput) {
        int fileIdx = rawInput.substring(0, 1).charAt(0) - 'a';
        int rankIdx = Integer.parseInt(rawInput.substring(1, 2)) - 1;
        File file = File.values()[fileIdx];
        Rank rank = Rank.values()[rankIdx];

        return Position.of(file, rank);
    }
}
