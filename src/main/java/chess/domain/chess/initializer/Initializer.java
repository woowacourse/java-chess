package chess.domain.chess.initializer;

import chess.domain.chess.Team;
import chess.domain.chess.unit.Unit;
import chess.domain.geometric.Position;

import java.util.Map;

public interface Initializer {
    Map<Position, Unit> create();
    Team createTeam();
}
