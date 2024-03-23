package chess.domain.piece;

import java.util.Collections;
import java.util.List;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;

class AbstractSlidingPiece extends AbstractPiece {

    private final List<Direction> directions;

    public AbstractSlidingPiece(PieceType type, Team team, List<Direction> directions) {
        super(type, team);

        this.directions = directions;
    }

    @Override
    void validatePieceMoveRule(Coordinate source, Coordinate target, Board board) {
        List<Coordinate> slidingPath = createSlidingPath(source, target);
        validateReachable(target, slidingPath);
        validateBlocked(target, slidingPath, board);
    }

    private List<Coordinate> createSlidingPath(Coordinate source, Coordinate target) {
        return directions.stream()
                .map(possibleDirection -> possibleDirection.createSlidingPath(source))
                .filter(coordinates -> coordinates.contains(target))
                .findFirst()
                .orElse(Collections.emptyList());
    }

    private void validateReachable(Coordinate target, List<Coordinate> possiblePath) {
        if (!possiblePath.contains(target)) {
            throw new IllegalStateException("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
        }
    }

    private void validateBlocked(Coordinate target, List<Coordinate> slidingPath, Board board) {
        Coordinate blockedCoordinate = slidingPath.stream()
                .filter(board::isPiecePresent)
                .findFirst()
                .orElse(target);

        if (!blockedCoordinate.equals(target)) {
            throw new IllegalStateException("기물로 막혀있어 이동할 수 없습니다.");
        }
    }
}
