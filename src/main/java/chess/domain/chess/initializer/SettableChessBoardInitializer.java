package chess.domain.chess.initializer;

import chess.domain.chess.Team;
import chess.domain.chess.unit.Unit;
import chess.domain.geometric.Position;

import java.util.Map;

public class SettableChessBoardInitializer implements Initializer {
    private Map<Position, Unit> boardInformation;
    private Team team;

    public SettableChessBoardInitializer(Map<Position, Unit> boardInformation, Team team) {
        this.boardInformation = boardInformation;
        this.team = team;
    }

    @Override
    public Map<Position, Unit> create() {
        return boardInformation;
    }

    @Override
    public Team createTeam() {
        return team;
    }
}
