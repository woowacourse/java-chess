package chess.domain.chess.initializer;

import chess.domain.chess.Team;
import chess.domain.chess.unit.Unit;
import chess.domain.geometric.Position;

import java.util.Map;

public class ChessBoardDB implements Initializer {
    private Map<Position, Unit> map;
    private Team team;

    public ChessBoardDB(Map<Position, Unit> map, Team team) {
        this.map = map;
        this.team = team;
    }

    @Override
    public Map<Position, Unit> create() {
        return map;
    }

    @Override
    public Team createTeam() {
        return team;
    }
}
