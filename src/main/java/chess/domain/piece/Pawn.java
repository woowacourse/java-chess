package chess.domain.piece;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import chess.domain.board.Coordinate;

public class Pawn extends AbstractPiece {

    public static final List<Map.Entry<Integer, Integer>> WEIGHTS = List.of(
            Map.entry(1, 0),
            Map.entry(2, 0)
    );

    public Pawn(Team team) {
        super(PieceType.PAWN, team);
    }

    @Override
    public List<Coordinate> findMovablePath(Coordinate start, Coordinate destination) {
        int startRank = start.getRank();
        char startFile = start.getFile();

        return WEIGHTS.stream()
                .map(weight -> mapToNextCoordinate(startRank, startFile, weight))
                .filter(coordinate -> !Objects.isNull(coordinate))
                .toList();
    }

    private Coordinate mapToNextCoordinate(int startRank, char startFile, Map.Entry<Integer, Integer> weight) {
        try {
            int rankValue = calculateNextRank(startRank, weight);
            char fileValue = calculateNextFile(startFile, weight);
            return new Coordinate(rankValue, fileValue);
        } catch (IllegalArgumentException ignored) {
            return null;
        }
    }

    private int calculateNextRank(int startRank, Map.Entry<Integer, Integer> weight) {
        int forwardDirection = getTeam().getForwardDirection();

        return startRank + weight.getKey() * forwardDirection;
    }

    private char calculateNextFile(char startFile, Map.Entry<Integer, Integer> weight) {
        return (char) (startFile + weight.getValue());
    }
}
