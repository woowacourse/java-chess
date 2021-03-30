package chess.domain.grid.gridStrategy;

import chess.domain.grid.Line;
import chess.domain.grid.Row;

import java.util.ArrayList;
import java.util.List;

public class TestGridStrategy implements GridStrategy {
    private static final int FIRST_ROW = 1;
    private static final int EIGHTH_ROW = 8;

    @Override
    public List<Line> linesInInitGrid() {
        List<Line> lineGroup = new ArrayList<>();
        for (int i = FIRST_ROW; i <= EIGHTH_ROW; i++) {
            lineGroup.add(Line.empty(Row.row(i)));
        }
        return lineGroup;
    }
}
