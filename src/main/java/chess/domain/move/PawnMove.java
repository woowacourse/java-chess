package chess.domain.move;

import chess.domain.piece.Position;

import java.util.List;
import java.util.Set;

import static chess.domain.move.Move.PAWN_MAX_MOVE_COUNT;

public class PawnMove implements Movable {
    private static final int ATTACK_INDEX = 1;
    private static final Move move = new Move();

    /**
     * 폰의 이동 과정
     * - 출발 위치의 행과 도착 위치의 행의 차가 1이라면 1만큼 이동한다.
     * - 출발 위치의 행보다 도착 위치의 행이 더 크면 2만큼 UP 방향으로 이동한다.
     * - 출발 위치의 행보다 도착 위치의 행이 더 작으면 3만큼 DOWN 방향으로 이동한다.
     *
     * @param source 출발 위치
     * @param target 도착 위치
     * @return 이동 가능 여부
     */
    @Override
    public boolean canMove(final Position source, final Position target) {
        // UP
        if (source.getRank() < target.getRank()) {
            final Set<Position> allPositions = move.getAllPositions(source,
                    List.of(Direction.UP, Direction.UP_LEFT, Direction.UP_RIGHT), PAWN_MAX_MOVE_COUNT);
            return allPositions.contains(target);
        }
        // DOWN
        final Set<Position> allPositions = move.getAllPositions(source,
                List.of(Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_RIGHT), PAWN_MAX_MOVE_COUNT);
        return allPositions.contains(target);
    }

    @Override
    public boolean canAttack(final Position source, final Position target) {
        return Math.abs(target.getRank() - source.getRank()) == ATTACK_INDEX
                && Math.abs(target.getFile() - source.getFile()) == ATTACK_INDEX;
    }

    @Override
    public boolean isPossibleRoute(final Position source, final Position target, final boolean isPossible) {
        return canMove(source, target);
    }
}
