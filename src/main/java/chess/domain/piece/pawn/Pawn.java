package chess.domain.piece.pawn;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.piece.Piece;
import chess.domain.square.Rank;

import static chess.domain.Team.*;
import static chess.domain.movement.Movement.CONTINUOUS_STRAIGHT;
import static chess.domain.movement.Movement.DISCONTINUOUS_DIAGONAL;
import static chess.domain.piece.PieceType.PAWN;

public class Pawn extends Piece {

    private static final int MOVABLE_RANK_BOUNDARY = 0;
    private static final int IMMOVABLE_FILE_BOUNDARY = 0;
    private static final int IMMOVABLE_RANK_BOUNDARY = 2;

    public Pawn(final Team team) {
        super(team, PAWN);
    }

    @Override
    public boolean isValidMove(final int fileInterval, final int rankInterval, final Piece target) {
        Movement movement = Movement.of(fileInterval, rankInterval);
        if (!getPieceType().getMovements().contains(movement)) {
            throw new IllegalArgumentException("말이 이동할 수 없는 규칙입니다.");
        }
        validateRankMovement(rankInterval);
        validateAttackMovement(target, movement);
        validateFileMovement(fileInterval, movement);
        return true;
    }

    private void validateRankMovement(final int rankInterval) {
        if (getTeam() == WHITE && rankInterval < MOVABLE_RANK_BOUNDARY) {
            throw new IllegalArgumentException("화이트 폰은 위로만 움직일 수 있습니다.");
        }

        if (getTeam() == BLACK && rankInterval > MOVABLE_RANK_BOUNDARY) {
            throw new IllegalArgumentException("블랙 폰은 아래로만 움직일 수 있습니다.");
        }
    }

    private void validateAttackMovement(final Piece target, final Movement movement) {
        if (isOppositeTeam(target) && !DISCONTINUOUS_DIAGONAL.equals(movement)) {
            throw new IllegalArgumentException("폰의 공격은 대각선으로만 가능합니다.");
        }

        if (DISCONTINUOUS_DIAGONAL.equals(movement) && !isOppositeTeam(target)) {
            throw new IllegalArgumentException("상대 팀이 위치하는 경우만 대각선으로 이동할 수 있습니다.");
        }
    }

    private static void validateFileMovement(final int fileInterval, final Movement movement) {
        if (!DISCONTINUOUS_DIAGONAL.equals(movement) && Math.abs(fileInterval) > IMMOVABLE_FILE_BOUNDARY) {
            throw new IllegalArgumentException("말이 이동할 수 없는 규칙입니다.");
        }
    }

    private boolean isOppositeTeam(final Piece target) {
        return Team.change(getTeam()) == target.getTeam() && target.getTeam() != EMPTY;
    }

    @Override
    public boolean isValidTeam(final Piece target) {
        return getTeam() != target.getTeam();
    }

    @Override
    public boolean isValidPawnMove(final Rank rank, final int fileInterval, final int rankInterval) {
        Movement movement = Movement.of(fileInterval, rankInterval);
        if (isInitialPawn(rank)) {
            if (CONTINUOUS_STRAIGHT.equals(movement) && Math.abs(rankInterval) > IMMOVABLE_RANK_BOUNDARY) {
                throw new IllegalArgumentException("초기 폰은 최대 2칸까지 이동할 수 있습니다.");
            }
            return true;
        }
        if (CONTINUOUS_STRAIGHT.equals(movement)) {
            throw new IllegalArgumentException("초기 폰이 아닌 경우, 폰은 최대 1칸까지 앞으로 이동할 수 있습니다.");
        }
        return true;
    }

    private boolean isInitialPawn(final Rank rank) {
        return rank == Rank.TWO || rank == Rank.SEVEN;
    }
}
