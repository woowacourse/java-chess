package chess.domain.piece;

import java.util.List;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Coordinate;
import chess.domain.piece.exception.InvalidMoveException;
import chess.domain.piece.exception.ObstacleException;

public class Pawn extends AbstractPiece {

    private final List<Direction> forwardDirections = List.of(
            Direction.UP,
            Direction.UP_UP
    );

    private final List<Direction> diagonalDirections = List.of(
            Direction.UP_LEFT,
            Direction.UP_RIGHT
    );

    public Pawn(Team team) {
        super(PieceType.PAWN, team);
    }

    @Override
    void validatePieceMoveRule(Coordinate source, Coordinate target, Board board) {
        List<Coordinate> forwardPath = createPath(source, forwardDirections);
        List<Coordinate> diagonalPath = createPath(source, diagonalDirections);

        validateReachable(target, diagonalPath, forwardPath);
        validateForwardAttack(target, forwardPath, board);
        if (isTwoStep(source, target)) {
            validateInitialCoordinate(source);
            validateBlocked(target, forwardPath, board);
        }
        validateDiagonal(target, diagonalPath, board);
    }

    private List<Coordinate> createPath(Coordinate source, List<Direction> directions) {
        int forwardDirection = team.getForwardDirection();

        return directions.stream()
                .map(Direction::getWeight)
                .map(weight -> weight.multiplyAtRankWeight(forwardDirection))
                .filter(source::canPlus)
                .map(source::plus)
                .toList();
    }

    private void validateReachable(Coordinate target, List<Coordinate> diagonalPath, List<Coordinate> forwardPath) {
        if (!(forwardPath.contains(target) || diagonalPath.contains(target))) {
            throw new InvalidMoveException();
        }
    }

    private void validateForwardAttack(Coordinate target, List<Coordinate> forwardPath, Board board) {
        if (forwardPath.contains(target) && isEnemy(board.findByCoordinate(target))) {
            throw new ObstacleException();
        }
    }

    private boolean isTwoStep(Coordinate source, Coordinate target) {
        return Math.abs(source.getRank() - target.getRank()) == 2;
    }

    private void validateInitialCoordinate(Coordinate source) {
        if (!BoardFactory.isInitialRank(source)) {
            throw new IllegalStateException("초기 상태의 폰이 아닌 경우, 2칸 이동할 수 없습니다.");
        }
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

    private void validateDiagonal(Coordinate target, List<Coordinate> diagonalPath, Board board) {
        if (diagonalPath.contains(target)) {
            validateEnemyExist(target, board);
        }
    }

    private void validateEnemyExist(Coordinate target, Board board) {
        if (!board.isPiecePresent(target)) {
            throw new InvalidMoveException();
        }

        if (!isEnemy(board.findByCoordinate(target))) {
            throw new InvalidMoveException();
        }
    }
}
