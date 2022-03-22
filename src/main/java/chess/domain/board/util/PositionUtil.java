package chess.domain.board.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PositionUtil {

    private static final List<Character> ranks = toCharacters("abcdefgh");
    private static final List<Character> files = toCharacters("12345678");

    private static final Map<Character, Integer> rankMap = new HashMap<>(8);
    private static final Map<Character, Integer> fileMap = new HashMap<>(8);

    private static final String INVALID_POSITION_RANGE_EXCEPTION_MESSAGE = "존재하지 않는 포지션 입니다. (a1~h8)";

    static {
        IntStream.rangeClosed(0, 7)
                .peek(PositionUtil::initializeRankMapValue)
                .forEach(PositionUtil::initializeFileMapValue);
    }

    private PositionUtil() {
    }

    private static void initializeRankMapValue(int idx) {
        Character key = ranks.get(idx);
        rankMap.put(key, idx);
    }

    private static void initializeFileMapValue(int idx) {
        Character key = files.get(idx);
        fileMap.put(key, idx);
    }

    private static List<Character> toCharacters(String value) {
        return value.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
    }

    public static int rankToInt(char rank) {
        Integer intValue = rankMap.get(rank);
        validateRange(intValue);
        return intValue;
    }

    public static int fileToInt(char file) {
        Integer intValue = fileMap.get(file);
        validateRange(intValue);
        return intValue;
    }

    private static void validateRange(Integer intValue) {
        if (intValue == null) {
            throw new IllegalArgumentException(INVALID_POSITION_RANGE_EXCEPTION_MESSAGE);
        }
    }
}
