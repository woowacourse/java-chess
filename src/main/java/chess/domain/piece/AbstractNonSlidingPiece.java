package chess.domain.piece;

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

    private List<Coordinate> createPath(Coordinate source) {
        return directions.stream()
                .map(Direction::getWeight)
                .filter(source::isApplicable)
                .map(source::apply)
                .toList();
    }
}
