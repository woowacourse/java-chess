package domain.path.pathValidtor.validateTarget;

import java.util.List;
import domain.board.piece.Camp;
import domain.board.piece.Piece;
import domain.path.Path;
import domain.path.direction.Direction;

public final class PawnMoveValidation implements ValidateTarget {

    private static final int INITIAL_BONUS_MOVE_COUNT = 2;
    private static final int NORMAL_MOVE_COUNT = 1;
    private static final int WHITE_PAWN_INITIAL_ROW = 1;
    private static final int BLACK_PAWN_INITIAL_ROW = 6;

    @Override
    public void validate(final Path path) {
        if (isAttack(path) || isMove(path)) {
            return;
        }
        throw new IllegalArgumentException("폰이 움직일 수 없는 경로입니다.");
    }

    private boolean isAttack(final Path path) {
        final Piece startPiece = path.getPiecesInPath().get(0);
        if (startPiece.getCamp() == Camp.WHITE) {
            return isWhiteAttack(path);
        }
        if (startPiece.getCamp() == Camp.BLACK) {
            return isBlackAttack(path);
        }
        throw new IllegalArgumentException("폰의 진영은 WHITE, BLACK 중 하나여야 합니다.");
    }

    private boolean isWhiteAttack(final Path path) {
        final List<Direction> whiteAttackDirections = List.of(
                Direction.LEFT_UP,
                Direction.RIGHT_UP
        );
        return whiteAttackDirections.contains(path.getDirection())
                && path.getMoveCount() == NORMAL_MOVE_COUNT
                && path.getPiecesInPath().get(NORMAL_MOVE_COUNT).getCamp() == Camp.BLACK;
    }

    private boolean isBlackAttack(final Path path) {
        final List<Direction> blackAttackDirections = List.of(
                Direction.LEFT_DOWN,
                Direction.RIGHT_DOWN
        );
        return blackAttackDirections.contains(path.getDirection())
                && path.getMoveCount() == NORMAL_MOVE_COUNT
                && path.getPiecesInPath().get(NORMAL_MOVE_COUNT).getCamp() == Camp.WHITE;
    }


    private boolean isMove(final Path path) {
        final List<Piece> piecesInPath = path.getPiecesInPath();
        final Piece pawn = piecesInPath.get(0);
        if (pawn.getCamp() == Camp.WHITE) {
            return isWhiteMove(path);
        }
        if (pawn.getCamp() == Camp.BLACK) {
            return isBlackMove(path);
        }
        throw new IllegalArgumentException("폰의 진영은 WHITE, BLACK 중 하나여야 합니다.");
    }

    private boolean isWhiteMove(final Path path) {
        return isWhiteNormalMove(path) || isWhiteInitialMove(path);
    }

    private static boolean isWhiteNormalMove(final Path path) {
        return path.getDirection() == Direction.UP
                && path.getMoveCount() == NORMAL_MOVE_COUNT
                && path.getPiecesInPath().get(NORMAL_MOVE_COUNT).getCamp() == Camp.NONE;
    }

    private static boolean isWhiteInitialMove(final Path path) {
        return path.getStartLocation().getRow() == WHITE_PAWN_INITIAL_ROW
                && path.getDirection() == Direction.UP
                && path.getMoveCount() == INITIAL_BONUS_MOVE_COUNT
                && path.getPiecesInPath().get(INITIAL_BONUS_MOVE_COUNT).getCamp() == Camp.NONE;
    }

    private boolean isBlackMove(final Path path) {
        return isBlackNormalMove(path) || isBlackInitialMove(path);
    }

    private static boolean isBlackNormalMove(final Path path) {
        return path.getDirection() == Direction.DOWN
                && path.getMoveCount() == NORMAL_MOVE_COUNT
                && path.getPiecesInPath().get(NORMAL_MOVE_COUNT).getCamp() == Camp.NONE;
    }

    private static boolean isBlackInitialMove(final Path path) {
        return path.getStartLocation().getRow() == BLACK_PAWN_INITIAL_ROW
                && path.getDirection() == Direction.DOWN
                && path.getMoveCount() == INITIAL_BONUS_MOVE_COUNT
                && path.getPiecesInPath().get(INITIAL_BONUS_MOVE_COUNT).getCamp() == Camp.NONE;
    }
}
