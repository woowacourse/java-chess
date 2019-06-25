package chess.domain.chess.initializer;

import chess.domain.chess.unit.Unit;
import chess.domain.geometric.Position;

import java.util.Map;

public class ChessBoardDB implements Initializer {
    private Map<Position, Unit> map;

    public ChessBoardDB(Map<Position, Unit> map) {
        this.map = map;
    }

    @Override
    public Map<Position, Unit> create() {
        return map;
    }
}
