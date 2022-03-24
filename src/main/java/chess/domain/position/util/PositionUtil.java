package chess.domain.position.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PositionUtil {

    private static final int BOARD_SIZE = 8;
    public static final int RANKS_TOTAL_SIZE = BOARD_SIZE;
    public static final int FILES_TOTAL_SIZE = BOARD_SIZE;
    private static final String INVALID_POSITION_RANGE_EXCEPTION_MESSAGE = "존재하지 않는 포지션입니다. (a1~h8)";

    private static final String VALID_FILES = "abcdefgh";
    private static final String VALID_RANKS = "12345678";

    private static final String POSITION_INPUT_FORMAT_REGEX = "([" + VALID_FILES + "][" + VALID_RANKS + "])";
    private static final Pattern PATTERN = Pattern.compile(POSITION_INPUT_FORMAT_REGEX);

    private static final List<Character> validFiles = toCharacters(VALID_FILES);
    private static final List<Character> validRanks = toCharacters(VALID_RANKS);

    private static final Map<Character, Integer> fileMap = new HashMap<>(FILES_TOTAL_SIZE);
    private static final Map<Character, Integer> rankMap = new HashMap<>(RANKS_TOTAL_SIZE);

    private static final int FILE_KEY_IDX = 0;
    private static final int RANK_KEY_IDX = 1;

    static {
        IntStream.range(0, BOARD_SIZE)
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
        validatePositionFormat(positionKey);
        char file = positionKey.charAt(FILE_KEY_IDX);
        return fileMap.get(file);
    }

    public static int toRankIdx(String positionKey) {
        validatePositionFormat(positionKey);
        char rank = positionKey.charAt(RANK_KEY_IDX);
        return rankMap.get(rank);
    }

    public static void validatePositionFormat(String position) {
        Matcher matcher = PATTERN.matcher(position);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(INVALID_POSITION_RANGE_EXCEPTION_MESSAGE);
        }
    }
}
