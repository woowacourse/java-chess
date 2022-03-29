package chess.util;

import static chess.domain.board.position.Position.FILES_TOTAL_SIZE;
import static chess.domain.board.position.Position.RANKS_TOTAL_SIZE;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PositionConverterUtil {

    private static final String VALID_FILES = "abcdefgh";
    private static final String VALID_RANKS = "12345678";

    private static final int FILE_KEY_IDX = 0;
    private static final int RANK_KEY_IDX = 1;

    private static final String INVALID_POSITION_RANGE_EXCEPTION_MESSAGE = "존재하지 않는 포지션입니다. (a1~h8)";

    private static final String POSITION_INPUT_FORMAT_REGEX = "([" + VALID_FILES + "][" + VALID_RANKS + "])";
    private static final Pattern PATTERN = Pattern.compile(POSITION_INPUT_FORMAT_REGEX);

    private PositionConverterUtil() {
    }

    public static int toFileIdx(String positionKey) {
        validatePositionFormat(positionKey);
        char file = positionKey.charAt(FILE_KEY_IDX);
        return PositionConverterMap.fileIdxOf(file);
    }

    public static int toRankIdx(String positionKey) {
        validatePositionFormat(positionKey);
        char rank = positionKey.charAt(RANK_KEY_IDX);
        return PositionConverterMap.rankIdxOf(rank);
    }

    public static void validatePositionFormat(String position) {
        Matcher matcher = PATTERN.matcher(position);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(INVALID_POSITION_RANGE_EXCEPTION_MESSAGE);
        }
    }

    private static class PositionConverterMap {

        static final Map<Character, Integer> fileIdxMap;
        static final Map<Character, Integer> rankIdxMap;

        static {
            fileIdxMap = initializeFileIdxMap();
            rankIdxMap = initializeRankIdxMap();
        }

        static int fileIdxOf(char file) {
            return fileIdxMap.get(file);
        }

        static int rankIdxOf(char rank) {
            return rankIdxMap.get(rank);
        }

        static Map<Character, Integer> initializeFileIdxMap() {
            final Map<Character, Integer> fileMap = new HashMap<>(FILES_TOTAL_SIZE);
            final char[] fileKeys = VALID_FILES.toCharArray();

            for (int fileIdx = 0; fileIdx < FILES_TOTAL_SIZE; fileIdx++) {
                fileMap.put(fileKeys[fileIdx], fileIdx);
            }
            return fileMap;
        }

        static Map<Character, Integer> initializeRankIdxMap() {
            final Map<Character, Integer> rankMap = new HashMap<>(RANKS_TOTAL_SIZE);
            final char[] rankKeys = VALID_RANKS.toCharArray();

            for (int rankIdx = 0; rankIdx < RANKS_TOTAL_SIZE; rankIdx++) {
                rankMap.put(rankKeys[rankIdx], rankIdx);
            }
            return rankMap;
        }
    }
}
