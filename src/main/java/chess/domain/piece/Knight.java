package chess.domain.piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import chess.domain.board.Coordinate;

public class Knight extends AbstractPiece {

    private static final List<Map.Entry<Integer, Integer>> WEIGHTS = List.of(
            Map.entry(-2, -1),
            Map.entry(-2, 1),
            Map.entry(-1, -2),
            Map.entry(-1, 2),
            Map.entry(1, -2),
            Map.entry(1, 2),
            Map.entry(2, -1),
            Map.entry(2, 1)
    );

    public Knight(Team team) {
        super(PieceType.KNIGHT, team);
    }

    @Override
    public List<Coordinate> findMovablePath(Coordinate start, Coordinate destination) {
        List<Coordinate> possibleCoordinate = new ArrayList<>();
        int startRank = start.getRank();
        char startFile = start.getFile();

        for (Map.Entry<Integer, Integer> weight : WEIGHTS) {
            int nextRank = startRank + weight.getKey();
            char nextFile = (char) (startFile + weight.getValue());

            try {
                Coordinate coordinate = new Coordinate(nextRank, nextFile);
                possibleCoordinate.add(coordinate);
            } catch (IllegalArgumentException ignored) {
            }
        }

        if (possibleCoordinate.contains(destination)) {
            return List.of(destination);
        }

        return Collections.emptyList();
    }
}
