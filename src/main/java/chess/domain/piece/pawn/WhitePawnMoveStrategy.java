package chess.domain.piece.pawn;

import chess.domain.position.Path;
import chess.domain.position.Position;

import java.util.Set;

public final class WhitePawnMoveStrategy implements PawnMoveStrategy {

    private static final int PAWN_INITIAL_RANK = 2;
    private static final int FORWARD_TWO_SQUARES = 4;
    private static final int FILE_SUB_EXCLUDE = 2;
    private static final int RANK_DISTANCE = -1;

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        Path path = Path.of(source, target);
        if (source.distanceEquals(target, FORWARD_TWO_SQUARES) && source.isFileEquals(target)) {
            validateIsFirstMove(source);
            return path.computePath(source, target);
        }
        if (source.fileSubLessThan(target, FILE_SUB_EXCLUDE) && source.rankSubEquals(target, RANK_DISTANCE)) {
            return Set.of(target);
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
    }

    private void validateIsFirstMove(final Position source) {
        if (source.rankNotEquals(PAWN_INITIAL_RANK)) {
            throw new IllegalArgumentException("첫 이동이 아니면 두칸 전진 할 수 없습니다.");
        }
    }
}
