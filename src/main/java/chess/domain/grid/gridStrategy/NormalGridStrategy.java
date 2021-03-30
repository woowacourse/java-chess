package chess.domain.grid.gridStrategy;

import chess.domain.grid.Line;
import chess.domain.grid.Row;
import chess.domain.piece.Color;

import java.util.ArrayList;
import java.util.List;

public final class NormalGridStrategy implements GridStrategy {

    @Override
    public List<Line> linesInInitGrid() {
        List<Line> lineGroup = new ArrayList<>();
        lineGroup.add(Line.general(Row.EIGHTH, Color.BLACK));
        lineGroup.add(Line.pawn(Row.SEVENTH, Color.BLACK));
        lineGroup.add(Line.empty(Row.SIXTH));
        lineGroup.add(Line.empty(Row.FIFTH));
        lineGroup.add(Line.empty(Row.FOURTH));
        lineGroup.add(Line.empty(Row.THIRD));
        lineGroup.add(Line.pawn(Row.SECOND, Color.WHITE));
        lineGroup.add(Line.general(Row.FIRST, Color.WHITE));
        return lineGroup;
    }
}
