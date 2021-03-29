package chess.domain.grid.gridStrategy;

import chess.domain.grid.Line;
import chess.domain.grid.Row;
import chess.domain.piece.Color;
import chess.domain.state.GameState;
import chess.domain.state.Ready;

import java.util.ArrayList;
import java.util.List;

public class NormalGridStrategy implements GridStrategy {
    @Override
    public List<Line> linesInInitGrid() {
        List<Line> lineGroup = new ArrayList<>();
        lineGroup.add(Line.general(Row.EIGHTH, Color.BLACK));
        lineGroup.add(Line.pawn(Row.SEVENTH, Color.BLACK));
        for (int i = Row.SIXTH.index(); i >= Row.THIRD.index(); i--) {
            lineGroup.add(Line.empty(Row.row(i)));
        }
        lineGroup.add(Line.pawn(Row.SECOND, Color.WHITE));
        lineGroup.add(Line.general(Row.FIRST, Color.WHITE));
        return lineGroup;
    }

    @Override
    public GameState initGameState() {
        return new Ready();
    }

    @Override
    public Color turn() {
        return Color.WHITE;
    }
}
