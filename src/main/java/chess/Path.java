package chess;

import java.util.List;

public class Path {

    private List<Position> positions;

    public Path(final Position... positions) {
        this.positions = List.of(positions);
    }
}
