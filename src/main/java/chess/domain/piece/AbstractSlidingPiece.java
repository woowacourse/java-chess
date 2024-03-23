package chess.domain.piece;

import java.util.ArrayList;
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
        List<Coordinate> slidingPath = createTargetIncludedSlidingPath(source, target);

        validateBlocked(target, slidingPath, board);
    }

    private List<Coordinate> createTargetIncludedSlidingPath(Coordinate source, Coordinate target) {
        return directions.stream()
                .map(direction -> createSlidingPath(source, direction))
                .filter(coordinates -> coordinates.contains(target))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("해당 기물은 주어진 좌표로 이동할 수 없습니다."));
    }

    private List<Coordinate> createSlidingPath(Coordinate start, Direction direction) {
        List<Coordinate> coordinates = new ArrayList<>();
        Weight weight = direction.getValue();
        int rankWeight = weight.rankWeight();
        int fileWeight = weight.fileWeight();
        int nextRank = start.getRank() + rankWeight;
        char nextFile = (char) (start.getFile() + fileWeight);

        while (nextRank >= 1 && nextRank <= 8 && nextFile >= 'a' && nextFile <= 'h') {
            coordinates.add(new Coordinate(nextRank, nextFile));
            nextRank += rankWeight;
            nextFile += fileWeight;
        }

        return coordinates;
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
