package chess.domain.position;

import java.util.HashMap;
import java.util.Map;

public class Positions {
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final String SOURCE = "source";
    private static final String TARGET = "target";

    private final Map<String, Position> positions = new HashMap<>();

    private Positions(String[] token) {
        positions.put(SOURCE, makePosition(token, SOURCE_INDEX));
        positions.put(TARGET, makePosition(token, TARGET_INDEX));
    }

    public static Positions from(String[] token) {
        validatePosition(token[SOURCE_INDEX]);
        validatePosition(token[TARGET_INDEX]);

        return new Positions(token);
    }

    private Position makePosition(String[] token, int index) {
        File file = File.toFile(token[index].charAt(FILE_INDEX));
        Rank rank = Rank.toRank(token[index].charAt(RANK_INDEX));

        return Position.of(file, rank);
    }

    private static void validatePosition(String token) {
        char first = token.charAt(FILE_INDEX);
        char second = token.charAt(RANK_INDEX);

        if (!File.isFile(first) || !Rank.isRank(Character.getNumericValue(second))) {
            throw new IllegalArgumentException("형식이 잘못되었거나 범위를 벗어났습니다.");
        }
    }

    public Position getSource() {
        return positions.get(SOURCE);
    }

    public Position getTarget() {
        return positions.get(TARGET);
    }
}
