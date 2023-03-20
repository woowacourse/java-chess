package chess.controller.dto;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.List;

public class ChessBoardPositions {
    private static ChessBoardPositions chessBoardPositions;

    private final List<Position> positions;

    public ChessBoardPositions(List<Position> positions) {
        this.positions = positions;
    }

    public static ChessBoardPositions getInstance() {
        if (chessBoardPositions == null) {
            chessBoardPositions =  init();
            return chessBoardPositions;
        }

        return chessBoardPositions;
    }

    private static ChessBoardPositions init() {
        List<Position> positions = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            addPositions(positions, rank);
        }

        return new ChessBoardPositions(positions);
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
