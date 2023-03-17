package chess.view;

import chess.dto.PositionRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class PositionMapper {
    private static final String INVALID_POSITION_EXCEPTION_MESSAGE = "[ERROR] 올바른 위치 값이 아닙니다.";
    private static final Pattern POSITION_REGEX = Pattern.compile("^[a-h][1-8]$");
    private static final Map<String, Integer> FILE_MAPPER = new HashMap<>();
    private static final Map<String, Integer> RANK_MAPPER = new HashMap<>();
    private static final String DELIMITER = "";

    private PositionMapper() {
    }

    static {
        initializeFileMapper();
        initializeRankMapper();
    }

    private static void initializeRankMapper() {
        RANK_MAPPER.put("1", 0);
        RANK_MAPPER.put("2", 1);
        RANK_MAPPER.put("3", 2);
        RANK_MAPPER.put("4", 3);
        RANK_MAPPER.put("5", 4);
        RANK_MAPPER.put("6", 5);
        RANK_MAPPER.put("7", 6);
        RANK_MAPPER.put("8", 7);
    }

    private static void initializeFileMapper() {
        FILE_MAPPER.put("a", 0);
        FILE_MAPPER.put("b", 1);
        FILE_MAPPER.put("c", 2);
        FILE_MAPPER.put("d", 3);
        FILE_MAPPER.put("e", 4);
        FILE_MAPPER.put("f", 5);
        FILE_MAPPER.put("g", 6);
        FILE_MAPPER.put("h", 7);
    }

    public static PositionRequest map(String command) {
        validateCommand(command);
        String[] fileAndRank = command.split(DELIMITER);
        String file = fileAndRank[0];
        String rank = fileAndRank[1];
        return new PositionRequest(FILE_MAPPER.get(file), RANK_MAPPER.get(rank));
    }

    private static void validateCommand(String command) {
        if (!POSITION_REGEX.matcher(command).matches()) {
            throw new IllegalArgumentException(INVALID_POSITION_EXCEPTION_MESSAGE);
        }
    }
}
