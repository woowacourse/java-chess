package chess.domain.piece;

import java.util.List;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;

// TODO: depth 정리는 했는데, 아직도 개선할 점이 많아 보임!
public class Pawn extends AbstractPiece {

    private final List<Direction> forwardDirections = List.of(
            Direction.UP,
            Direction.UP_UP
    );

    private final List<Direction> diagonalDirections = List.of(
            Direction.UP_LEFT,
            Direction.UP_RIGHT
    );

    //TODO: 상수의 올바른 위치를 생각해보기.
    private static final int INITIAL_BLACK_PAWN_RANK = 7;
    private static final int INITIAL_WHITE_PAWN_RANK = 2;

    public Pawn(Team team) {
        super(PieceType.PAWN, team);
    }

    @Override
    void validatePieceMoveRule(Coordinate source, Coordinate target, Board board) {
        List<Coordinate> diagonalPath = createPath(source, diagonalDirections);
        List<Coordinate> forwardPath = createPath(source, forwardDirections);
        validateReachable(target, diagonalPath, forwardPath);
        validateDiagonal(target, board, diagonalPath);
        validateForward(source, target, board, forwardPath);
    }


    // TODO: AbstractNonSlidingPiece와 중복
    private List<Coordinate> createPath(Coordinate source, List<Direction> directions) {
        int forwardDirection = team.getForwardDirection();

        return directions.stream()
                .map(Direction::getWeight)
                .map(weight -> weight.multiplyAtRankWeight(forwardDirection))
                .filter(source::isApplicable)
                .map(source::apply)
                .toList();
    }

    private void validateReachable(Coordinate target, List<Coordinate> diagonalPath, List<Coordinate> forwardPath) {
        if (!(forwardPath.contains(target) || diagonalPath.contains(target))) {
            throw new IllegalStateException("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
        }
    }

    private void validateDiagonal(Coordinate target, Board board, List<Coordinate> diagonalPath) {
        if (diagonalPath.contains(target)) {
            validateEnemyExist(target, board);
        }
    }

    private void validateEnemyExist(Coordinate target, Board board) {
        if (!board.isPiecePresent(target)) {
            throw new IllegalStateException("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
        }

        if (!isEnemy(board.findByCoordinate(target))) {
            throw new IllegalStateException("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
        }
    }

    private void validateForward(Coordinate source, Coordinate target, Board board, List<Coordinate> forwardPath) {
        if (forwardPath.contains(target)) {
            validateInitialCoordinate(source, target);
            validateBlocked(target, forwardPath, board);
        }
    }

    private void validateInitialCoordinate(Coordinate source, Coordinate target) {
        if (isTwoStep(source, target) &&
                !(source.getRank() == INITIAL_BLACK_PAWN_RANK || source.getRank() == INITIAL_WHITE_PAWN_RANK)) {
            throw new IllegalStateException("초기 상태의 폰이 아닌 경우, 2칸 이동할 수 없습니다.");
        }
    }

    private boolean isTwoStep(Coordinate source, Coordinate target) {
        return Math.abs(source.getRank() - target.getRank()) == 2;
    }

    // TODO: AbstractSlidingPiece와 중복
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
