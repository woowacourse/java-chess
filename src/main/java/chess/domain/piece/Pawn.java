package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import chess.domain.Coordinate;

public class Pawn extends AbstractPiece {

    public static final List<Map.Entry<Integer, Integer>> WEIGHTS = List.of(
            Map.entry(1, 0),
            Map.entry(2, 0),
            Map.entry(1, -1),
            Map.entry(1, 1)
    );

    public Pawn(Team team) {
        super(PieceType.PAWN, team);
    }

    @Override
    public List<Coordinate> findAllPossibleCoordinate(Coordinate start) {
        int startRank = start.getRank();
        char startFile = start.getFile();

        List<Coordinate> possibleCoordinate = new ArrayList<>();

        for (Map.Entry<Integer, Integer> weight : WEIGHTS) {
            int nextRank = startRank + weight.getKey() * getTeam().getForwardDirection();
            char nextFile = (char) (startFile + weight.getValue());

            try {
                Coordinate coordinate = new Coordinate(nextRank, nextFile);
                possibleCoordinate.add(coordinate);
            } catch (IllegalArgumentException ignored) {
            }
        }
        return possibleCoordinate;

    }
}
