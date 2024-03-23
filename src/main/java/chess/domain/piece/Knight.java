package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import chess.domain.board.Board;
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
    void validatePieceMoveRule(Coordinate source, Coordinate target, Board board) {
        List<Coordinate> path = createPath(source);
        if (!path.contains(target)) {
            throw new IllegalStateException("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
        }
    }

    public List<Coordinate> createPath(Coordinate source) {
        List<Coordinate> possibleCoordinate = new ArrayList<>();
        int startRank = source.getRank();
        char startFile = source.getFile();

        for (Map.Entry<Integer, Integer> weight : WEIGHTS) {
            int nextRank = startRank + weight.getKey();
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
