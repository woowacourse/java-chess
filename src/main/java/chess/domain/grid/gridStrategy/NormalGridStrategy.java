package chess.domain.grid.gridStrategy;

import chess.domain.grid.Line;
import chess.domain.piece.Color;

import java.util.ArrayList;
import java.util.List;

public class NormalGridStrategy implements GridStrategy {
    private static final int FIRST_ROW = 1;
    private static final int SECOND_ROW = 2;
    private static final int THIRD_ROW = 3;
    private static final int SIXTH_ROW = 6;
    private static final int SEVENTH_ROW = 7;
    private static final int EIGHTH_ROW = 8;

    @Override
    public List<Line> LinesInInitGrid() {
        List<Line> lineGroup = new ArrayList<>();
        lineGroup.add(Line.general(EIGHTH_ROW, Color.BLACK));
        lineGroup.add(Line.pawn(SEVENTH_ROW, Color.BLACK));
        for (int i = SIXTH_ROW; i >= THIRD_ROW; i--) {
            lineGroup.add(Line.empty(i));
        }
        lineGroup.add(Line.pawn(SECOND_ROW, Color.WHITE));
        lineGroup.add(Line.general(FIRST_ROW, Color.WHITE));
        return lineGroup;
    }
}
