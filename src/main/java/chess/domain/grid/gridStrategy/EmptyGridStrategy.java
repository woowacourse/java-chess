package chess.domain.grid.gridStrategy;

import chess.domain.grid.Line;
import chess.domain.grid.Row;

import java.util.ArrayList;
import java.util.List;

public final class EmptyGridStrategy implements GridStrategy {

    @Override
    public List<Line> linesInInitGrid() {
        List<Line> lineGroup = new ArrayList<>();
        lineGroup.add(Line.empty(Row.EIGHTH));
        lineGroup.add(Line.empty(Row.SEVENTH));
        lineGroup.add(Line.empty(Row.SIXTH));
        lineGroup.add(Line.empty(Row.FIFTH));
        lineGroup.add(Line.empty(Row.FOURTH));
        lineGroup.add(Line.empty(Row.THIRD));
        lineGroup.add(Line.empty(Row.SECOND));
        lineGroup.add(Line.empty(Row.FIRST));
        return lineGroup;
    }
}

