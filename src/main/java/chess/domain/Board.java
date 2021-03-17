package chess.domain;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {

    private final List<Map<String, Position>> horizontals;

    public Board() {
        this.horizontals = Position.generate();
    }

    public List<Position> getHorizontal(int horizontalLine) {
        return new ArrayList<>(horizontals.get(horizontalLine).values());
    }
}
