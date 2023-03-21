package chess.view;

import chess.dto.PositionRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class PositionMapper {

    private static final Map<String, Integer> files = new HashMap<>();
    private static final Map<String, Integer> ranks = new HashMap<>();
    private static final Pattern REGEX = Pattern.compile("^[a-h][1-8]$");
    private static final String DELIMITER = "";

    private PositionMapper() {
    }

    static {
        files.put("a", 0);
        files.put("b", 1);
        files.put("c", 2);
        files.put("d", 3);
        files.put("e", 4);
        files.put("f", 5);
        files.put("g", 6);
        files.put("h", 7);

        ranks.put("1", 0);
        ranks.put("2", 1);
        ranks.put("3", 2);
        ranks.put("4", 3);
        ranks.put("5", 4);
        ranks.put("6", 5);
        ranks.put("7", 6);
        ranks.put("8", 7);
    }

    public static PositionRequest map(String command) {
        validateCommand(command);
        String[] fileAndRank = command.split(DELIMITER);
        String file = fileAndRank[0];
        String rank = fileAndRank[1];
        return new PositionRequest(files.get(file), ranks.get(rank));
    }

    private static void validateCommand(String command) {
        if (!REGEX.matcher(command).matches()) {
            throw new IllegalArgumentException("[ERROR] 올바른 위치 값이 아닙니다.");
        }
    }
}
