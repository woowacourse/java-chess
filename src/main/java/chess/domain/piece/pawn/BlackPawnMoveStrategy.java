package chess.domain.piece.pawn;

import chess.domain.board.Position;

import java.util.Set;

public final class BlackPawnMoveStrategy implements PawnMoveStrategy {

    private static final int PAWN_INITIAL_RANK = 7;
    private static final int FORAWRD_TWO_SQAURES = 4;
    private static final int FILE_DISTANCE_EXCLUDE = 2;
    private static final int RANK_DISTANCE = 1;

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        if (source.distanceEquals(target, FORAWRD_TWO_SQAURES) && source.isFileEquals(target)) {
            validateIsFirstMove(source);
            return source.generateFilePath(target);
        }
        if (source.fileSubLessThan(target, FILE_DISTANCE_EXCLUDE) && source.rankSubEquals(target, RANK_DISTANCE)) {
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
