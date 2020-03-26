package chess.domain.routeFinder;

import chess.domain.Route;
import chess.domain.Team;
import chess.domain.position.Position;

public interface RouteFinder {
    Route findRoute(Position fromPosition, Position toPosition, Team team);
}
