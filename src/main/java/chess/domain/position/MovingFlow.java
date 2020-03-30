package chess.domain.position;

import java.util.Arrays;
import java.util.List;

public class MovingFlow {
    private static final int SIZE = 2;
    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 1;

    private final List<Position> positions;

    private MovingFlow(List<Position> positions) {
        if (positions.size() != SIZE) {
            throw new IllegalArgumentException(String.format("동선은 %d개의 위치를 필요로 합니다.", SIZE));
        }
        this.positions = positions;
    }

    public static MovingFlow of(String from, String to) {
        return new MovingFlow(Arrays.asList(
                Position.of(from),
                Position.of(to)
        ));
    }

    public Position getFrom() {
        return positions.get(FROM_INDEX);
    }

    public Position getTo() {
        return positions.get(TO_INDEX);
    }


}
