package chess.dto;

import chess.domain.chess.unit.Unit;
import chess.domain.geometric.Position;

import java.util.Map;

public class ChessBoardDTO {
    private Map<Position, Unit> units;

    public Map<Position, Unit> getUnits() {
        return units;
    }

    public void setUnits(Map<Position, Unit> units) {
        this.units = units;
    }
}
