package chess.domain.board;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Positions {

    private static final String SPLIT_DELIMITER = " ";
    private static final String WRONG_PATTERN_MESSAGE = "이동 명령을 형식에 맞게 입력하세요.";
    private static final Pattern POSITION_PATTERN = Pattern.compile("[a-h][1-8]");
    private static final int VALID_SPLIT_COMMAND_SIZE = 3;

    private final List<Position> positions;

    public Positions(final List<Position> positions) {
        this.positions = positions;
    }

    public static Positions from(final String moveCommand) {
        List<String> commands = Arrays.asList(moveCommand.split(SPLIT_DELIMITER));

        validatePositions(commands);

        return new Positions(commands.stream()
            .filter(it -> POSITION_PATTERN.matcher(it).matches())
            .map(Position::from)
            .collect(Collectors.toList()));
    }

    private static void validatePositions(final List<String> commands) {
        if (commands.size() != VALID_SPLIT_COMMAND_SIZE) {
            throw new IllegalArgumentException(WRONG_PATTERN_MESSAGE);
        }
    }

    public Position before() {
        return positions.get(0);
    }

    public Position after() {
        return positions.get(1);
    }

    public int calculateColumnDistance() {
        return before().columnDistance(after());
    }

    public int calculateRowDistance() {
        return before().rowDistance(after());
    }

    public List<Position> calculatePath() {
        return before().pathTo(after());
    }

    public int calculateDirectedRowDistance() {
        return after().rowDirectedDistance(before());
    }
}
