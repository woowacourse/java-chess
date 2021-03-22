package chess.domain.grid.gridstrategy;

import chess.domain.grid.Line;
import chess.domain.grid.Row;
import chess.domain.grid.gridStrategy.GridStrategy;

import java.util.ArrayList;
import java.util.List;

public class TestGridStrategy implements GridStrategy {
    @Override
    public List<Line> LinesInInitGrid() {
        List<Line> lineGroup = new ArrayList<>();
        for (Row row : Row.values()) {
            lineGroup.add(Line.empty(row));
        }
        return lineGroup;
    }
}
