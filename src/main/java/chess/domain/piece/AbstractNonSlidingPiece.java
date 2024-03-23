package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;

abstract class AbstractNonSlidingPiece extends AbstractPiece {

    private final List<Direction> directions;

    public AbstractNonSlidingPiece(PieceType type, Team team, List<Direction> directions) {
        super(type, team);

        this.directions = directions;
    }

    @Override
    void validatePieceMoveRule(Coordinate source, Coordinate target, Board board) {
        List<Coordinate> path = createPath(source);
        if (!path.contains(target)) {
            throw new IllegalStateException("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
        }
    }

    /**
     * TODO: 이런 느낌으로 해보고 싶다..!
     *    if(coordinate.canApply(Weight weight)) {
     *        source.apply(Weight weight);
     *    };
     */
    private List<Coordinate> createPath(Coordinate source) {
        List<Coordinate> possibleCoordinate = new ArrayList<>();
        int startRank = source.getRank();
        char startFile = source.getFile();

        for (Direction direction : directions) {
            Weight weight = direction.getValue();
            int nextRank = startRank + weight.rankWeight();
            char nextFile = (char) (startFile + weight.fileWeight());

            try {
                Coordinate coordinate = new Coordinate(nextRank, nextFile);
                possibleCoordinate.add(coordinate);
            } catch (IllegalArgumentException ignored) {
            }
        }

        return possibleCoordinate;
    }
}
