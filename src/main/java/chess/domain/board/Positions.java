package chess.domain.board;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Positions {

    private static final String SPLIT_DELIMITER = " ";

    private final List<Position> positions;

    public Positions(final List<Position> positions) {
        this.positions = positions;
    }

    public static Positions from(final String command) {
        return new Positions(Arrays.stream(command.split(SPLIT_DELIMITER))
            .skip(1) // move a2 a4 -> a2, a4만 추출
            .map(Position::from)
            .collect(Collectors.toList()));
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
