package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.piece.exception.InvalidMoveException;
import chess.domain.piece.exception.ObstacleException;

abstract class AbstractSlidingPiece extends AbstractPiece {

    private final List<Direction> directions;

    public AbstractSlidingPiece(PieceType type, Team team, List<Direction> directions) {
        super(type, team);

        this.directions = directions;
    }

    @Override
    void validatePieceMoveRule(Coordinate source, Coordinate target, Board board) {
        List<Coordinate> path = createPath(source, target);

        validateBlocked(target, path, board);
    }

    private List<Coordinate> createPath(Coordinate source, Coordinate target) {
        return directions.stream()
                .map(direction -> createSlidingPath(source, direction))
                .filter(coordinates -> coordinates.contains(target))
                .findFirst()
                .orElseThrow(InvalidMoveException::new);
    }

    private List<Coordinate> createSlidingPath(Coordinate start, Direction direction) {
        List<Coordinate> slidingPath = new ArrayList<>();
        Weight weight = direction.getWeight();
        Coordinate nowCoordinate = start;

        while (nowCoordinate.isApplicable(weight)) {
            nowCoordinate = nowCoordinate.apply(weight);
            slidingPath.add(nowCoordinate);
        }

        return slidingPath;
    }

    private void validateBlocked(Coordinate target, List<Coordinate> path, Board board) {
        Coordinate blockedCoordinate = path.stream()
                .filter(board::isPiecePresent)
                .findFirst()
                .orElse(target);

        if (!blockedCoordinate.equals(target)) {
            throw new ObstacleException();
        }
    }
}
