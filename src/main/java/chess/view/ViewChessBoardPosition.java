package chess.view;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ViewChessBoardPosition {
    private final List<Position> positions;

    private ViewChessBoardPosition(List<Position> positions) {
        this.positions = positions;
    }

    public static ViewChessBoardPosition create() {
        return new ViewChessBoardPosition(Arrays.stream(Rank.values())
                .flatMap(file -> Arrays.stream(File.values())
                        .map(rank -> (new Position(rank, file))))
                .collect(Collectors.toList()));
    }

    public List<Position> getPositions() {
        return positions;
    }
}
