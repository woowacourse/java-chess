package chess.domain.chess.initializer;

import chess.domain.chess.Team;
import chess.domain.chess.unit.Unit;
import chess.domain.geometric.Position;

import java.util.HashMap;
import java.util.Map;

public class TestInitializer implements Initializer {
    private Map<Position, Unit> map;

    public TestInitializer(Map<Position, Unit> map) {
        this.map = map;
    }

    @Override
    public Map<Position, Unit> create() {
        return new HashMap<>(map);
    }

    @Override
    public Team createTeam() {
        return Team.WHITE;
    }
}
