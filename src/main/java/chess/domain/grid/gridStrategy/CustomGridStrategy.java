package chess.domain.grid.gridStrategy;

import chess.domain.grid.Line;
import chess.domain.state.GameState;
import chess.domain.state.Playing;

import java.util.ArrayList;
import java.util.List;

public class CustomGridStrategy implements GridStrategy {
    private final List<Line> lines;

    public CustomGridStrategy(List<Line> lines) {
        this.lines = new ArrayList(lines);
    }

    @Override
    public List<Line> linesInInitGrid() {
        return new ArrayList<>(lines);
    }

    @Override
    public GameState initGameState() {
        return new Playing();
    }
}
