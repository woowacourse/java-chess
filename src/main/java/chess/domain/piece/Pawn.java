package chess.domain.piece;

import java.util.List;
import chess.domain.board.Coordinate;
import chess.domain.board.Pieces;
import chess.domain.board.PiecesFactory;
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
    void validatePieceMoveRule(Coordinate source, Coordinate target, Pieces pieces) {
        List<Coordinate> forwardPath = createPath(source, forwardDirections);
        List<Coordinate> diagonalPath = createPath(source, diagonalDirections);

        validateReachable(target, diagonalPath, forwardPath);
        validateForwardAttack(target, forwardPath, pieces);
        if (isTwoStep(source, target)) {
            validateInitialCoordinate(source);
            validateBlocked(target, forwardPath, pieces);
        }
        validateDiagonal(target, diagonalPath, pieces);
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

    private void validateForwardAttack(Coordinate target, List<Coordinate> forwardPath, Pieces pieces) {
        if (forwardPath.contains(target) && isEnemy(pieces.findByCoordinate(target))) {
            throw new ObstacleException();
        }
    }

    private boolean isTwoStep(Coordinate source, Coordinate target) {
        return Math.abs(source.getRank() - target.getRank()) == 2;
    }

    private void validateInitialCoordinate(Coordinate source) {
        if (!PiecesFactory.isInitialRank(source)) {
            throw new IllegalStateException("초기 상태의 폰이 아닌 경우, 2칸 이동할 수 없습니다.");
        }
    }

    private void validateBlocked(Coordinate target, List<Coordinate> path, Pieces pieces) {
        Coordinate blockedCoordinate = path.stream()
                .filter(pieces::isPiecePresent)
                .findFirst()
                .orElse(target);

        if (!blockedCoordinate.equals(target)) {
            throw new ObstacleException();
        }
    }

    private void validateDiagonal(Coordinate target, List<Coordinate> diagonalPath, Pieces pieces) {
        if (diagonalPath.contains(target)) {
            validateEnemyExist(target, pieces);
        }
    }

    private void validateEnemyExist(Coordinate target, Pieces pieces) {
        if (!pieces.isPiecePresent(target)) {
            throw new InvalidMoveException();
        }

        if (!isEnemy(pieces.findByCoordinate(target))) {
            throw new InvalidMoveException();
        }
    }
}
