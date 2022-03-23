package chess.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

public class PositionParser {
    private static final String WRONG_POSITION = "올바르지 않은 위치 정보입니다.";
    static Map<Character, Integer> files = new HashMap<>();
    static Map<Character, Integer> ranks = new HashMap<>();

    static {
        files.put('a', 0);
        files.put('b', 1);
        files.put('c', 2);
        files.put('d', 3);
        files.put('e', 4);
        files.put('f', 5);
        files.put('g', 6);
        files.put('h', 7);

        ranks.put('8', 0);
        ranks.put('7', 1);
        ranks.put('6', 2);
        ranks.put('5', 3);
        ranks.put('4', 4);
        ranks.put('3', 5);
        ranks.put('2', 6);
        ranks.put('1', 7);
    }

    public static Pair<Integer, Integer> parse(char x, char y) {
        if (!files.containsKey(x) || !ranks.containsKey(y)) {
            throw new IllegalArgumentException(WRONG_POSITION);
        }

        return Pair.of(files.get(x), files.get(y));
    }
}
