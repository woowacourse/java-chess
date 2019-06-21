package chess.domain.chess.initializer;

import chess.domain.geometric.Position;
import chess.domain.chess.unit.Unit;

import java.util.Map;

public interface Initializer {
    Map<Position, Unit> create();
}
