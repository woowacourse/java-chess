package chess.view.output;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.List;

public class ConsoleViewChessBoardPositions {
    private static ConsoleViewChessBoardPositions consoleViewChessBoardPositions;

    private final List<Position> positions;

    private ConsoleViewChessBoardPositions(List<Position> positions) {
        this.positions = positions;
    }

    public static ConsoleViewChessBoardPositions getInstance() {
        if (consoleViewChessBoardPositions == null) {
            consoleViewChessBoardPositions =  init();
            return consoleViewChessBoardPositions;
        }

        return consoleViewChessBoardPositions;
    }

    private static ConsoleViewChessBoardPositions init() {
        List<Position> positions = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            addPositions(positions, rank);
        }

        return new ConsoleViewChessBoardPositions(positions);
    }

    private static void addPositions(List<Position> positions, Rank rank) {
        for (File file : File.values()) {
            positions.add(Position.of(file, rank));
        }
    }

    public List<Position> getPositions() {
        return positions;
    }
}
