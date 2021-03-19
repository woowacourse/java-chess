package chess.domain.board;

import java.util.List;

public class Positions {
    private final List<Position> positions;

    public Positions(List<Position> positions) {
        this.positions = positions;
    }

    public Position at(int index) {
        return positions.get(index);
    }
}
