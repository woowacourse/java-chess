package chess.domain.grid.gridStrategy;

import chess.domain.grid.Line;
import chess.domain.grid.Row;
import chess.domain.piece.Color;
import chess.domain.state.GameState;
import chess.domain.state.Playing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestGridStrategy implements GridStrategy {
    @Override
    public List<Line> linesInInitGrid() {
        List<Line> lineGroup = new ArrayList<>();

        List<Row> rows = Arrays.asList(Row.values());
        Collections.reverse(rows);
        for (Row row : rows) {
            lineGroup.add(Line.empty(row));
        }
        return lineGroup;
    }

    @Override
    public GameState initGameState() {
        return new Playing();
    }

    @Override
    public Color turn() {
        return Color.WHITE;
    }
}

