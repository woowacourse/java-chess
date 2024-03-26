package chess.domain.piece;

import java.util.List;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.piece.exception.InvalidMoveException;

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
            throw new InvalidMoveException();
        }
    }

    private List<Coordinate> createPath(Coordinate source) {
        return directions.stream()
                .map(Direction::getWeight)
                .filter(source::canPlus)
                .map(source::plus)
                .toList();
    }
}
