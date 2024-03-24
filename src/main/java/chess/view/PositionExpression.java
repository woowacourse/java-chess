package chess.view;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.HashMap;
import java.util.Map;

public class PositionExpression {

    private static final Map<String, Position> CACHE = new HashMap<>();

    private PositionExpression() {
    }

    public static Position mapToPosition(String rawInput) {
        int fileIdx = rawInput.substring(0, 1).charAt(0) - 'a';
        int rankIdx = Integer.parseInt(rawInput.substring(1, 2)) - 1;
        File file = File.values()[fileIdx];
        Rank rank = Rank.values()[rankIdx];

        return CACHE.computeIfAbsent(rawInput, key -> Position.of(file, rank));
    }
}
