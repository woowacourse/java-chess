package chess.domain.move;

import chess.domain.piece.Position;

import java.util.List;
import java.util.Set;

public class PawnMove implements Movable {
    private static final int PAWN_MAX_MOVE_COUNT = 1;

    private final Move move;

    public PawnMove() {
        this.move = new Move();
    }

    /**
     * 폰은 출발 위치의 행보다 도착 위치의 행이 더 크면 UP 방향, 아니면 DOWN 방향으로 이동한다.
     *
     * @param source 출발 위치
     * @param target 도착 위치
     * @return 이동 가능 여부
     */
    @Override
    public boolean canMove(final Position source, final Position target) {
        // UP
        if (source.getRank() < target.getRank()) {
            final Set<Position> allPositions = move.getAllPositions(source, List.of(Direction.UP), PAWN_MAX_MOVE_COUNT);
            return allPositions.contains(target);
        }
        // DOWN
        final Set<Position> allPositions = move.getAllPositions(source, List.of(Direction.DOWN), PAWN_MAX_MOVE_COUNT);
        return allPositions.contains(target);
    }
}
