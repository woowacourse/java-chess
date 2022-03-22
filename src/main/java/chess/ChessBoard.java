package chess;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> cells = new HashMap<>();

    public ChessBoard() {
    }

    public int init() {
        List<Position> positions = stream(File.values())
                .flatMap(file -> stream(Rank.values())
                        .map(rank -> new Position(rank, file)))
                                .collect(toList());

        for (Position position : positions) {
            if (position.isInitPosition()) {
                cells.put(position, new Piece());
            }
        }

        return cells.size();
    }
}
