package chess.domain.grid.gridStrategy;

import chess.domain.grid.Line;
import chess.domain.piece.Color;
import chess.domain.state.GameState;
import chess.domain.state.Playing;

import java.util.ArrayList;
import java.util.List;

public class CustomGridStrategy implements GridStrategy {
    private final List<Line> lines;
    private final Color turn;

    public CustomGridStrategy(List<Line> lines, Color turn) {
        this.lines = new ArrayList(lines);
        this.turn = turn;
    }

    @Override
    public List<Line> linesInInitGrid() {
        return new ArrayList<>(lines);
    }

    @Override
    public GameState initGameState() {
        return new Playing();
    }

    @Override
    public Color turn() {
        return turn;
    }
}
