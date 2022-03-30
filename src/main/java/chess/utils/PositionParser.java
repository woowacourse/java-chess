package chess.utils;

import chess.chessgame.Position;

import java.util.HashMap;
import java.util.Map;

public class PositionParser {
    private static final String WRONG_POSITION = "올바르지 않은 위치 정보입니다.";

    private static final Map<Character, Integer> ranks = new HashMap<>();
    private static final Map<Character, Integer> files = new HashMap<>();

    static {
        ranks.put('a', 0);
        ranks.put('b', 1);
        ranks.put('c', 2);
        ranks.put('d', 3);
        ranks.put('e', 4);
        ranks.put('f', 5);
        ranks.put('g', 6);
        ranks.put('h', 7);

        files.put('8', 0);
        files.put('7', 1);
        files.put('6', 2);
        files.put('5', 3);
        files.put('4', 4);
        files.put('3', 5);
        files.put('2', 6);
        files.put('1', 7);
    }

    public static Position parse(char x, char y) {
        if (!ranks.containsKey(x) || !files.containsKey(y)) {
            throw new IllegalArgumentException(WRONG_POSITION);
        }

        return new Position(files.get(y), ranks.get(x));
    }
}
