package chess.domain.chess.game.initializer;

import chess.domain.chess.game.Team;
import chess.domain.chess.unit.Unit;
import chess.domain.geometric.Position;

import java.util.Map;

public interface Initializer {
    Map<Position, Unit> create();

    Team createTeam();
}
