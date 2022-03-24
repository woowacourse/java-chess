package chess.domain.position.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PositionUtil {

    private static final int BOARD_SIZE = 8;
    public static final int RANKS_TOTAL_SIZE = BOARD_SIZE;
    public static final int FILES_TOTAL_SIZE = BOARD_SIZE;
    private static final String INVALID_POSITION_RANGE_EXCEPTION_MESSAGE = "존재하지 않는 포지션입니다. (a1~h8)";

    private static final List<Character> validFiles = toCharacters("abcdefgh");
    private static final List<Character> validRanks = toCharacters("12345678");

    private static final Map<Character, Integer> fileMap = new HashMap<>(FILES_TOTAL_SIZE);
    private static final Map<Character, Integer> rankMap = new HashMap<>(RANKS_TOTAL_SIZE);

    private static final int FILE_KEY_IDX = 0;
    private static final int RANK_KEY_IDX = 1;

    static {
        IntStream.range(FILE_KEY_IDX, BOARD_SIZE)
                .peek(PositionUtil::initializeRankMapValue)
                .forEach(PositionUtil::initializeFileMapValue);
    }

    private PositionUtil() {
    }

    private static void initializeRankMapValue(int idx) {
        Character key = validRanks.get(idx);
        rankMap.put(key, idx);
    }

    private static void initializeFileMapValue(int idx) {
        Character key = validFiles.get(idx);
        fileMap.put(key, idx);
    }

    private static List<Character> toCharacters(String value) {
        return value.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
    }

    public static int toFileIdx(String positionKey) {
        char file = positionKey.charAt(FILE_KEY_IDX);
        validateFileKey(file);
        return fileMap.get(file);
    }

    public static int toRankIdx(String positionKey) {
        char rank = positionKey.charAt(RANK_KEY_IDX);
        validateRankKey(rank);
        return rankMap.get(rank);
    }

    public static void validatePosition(String position) {
        validateFileKey(position.charAt(FILE_KEY_IDX));
        validateRankKey(position.charAt(RANK_KEY_IDX));
    }

    public static void validateFileKey(char file) {
        if (fileMap.get(file) == null) {
            throw new IllegalArgumentException(INVALID_POSITION_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public static void validateRankKey(char rank) {
        if (rankMap.get(rank) == null) {
            throw new IllegalArgumentException(INVALID_POSITION_RANGE_EXCEPTION_MESSAGE);
        }
    }
}
