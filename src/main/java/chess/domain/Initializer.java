package chess.domain;

import chess.domain.geometric.Position;
import chess.domain.unit.Unit;

import java.util.Map;

public interface Initializer {
    Map<Position, Unit> create();
}
