package chess.domain.model.piece.shape.strategy.pawn;

import static chess.domain.model.player.Color.BLACK;
import static chess.domain.model.player.Color.WHITE;

import chess.domain.model.player.Color;
import chess.domain.model.piece.shape.strategy.PieceStrategy;
import chess.domain.model.position.Position;

public class PawnStrategy implements PieceStrategy {

    private static final int WHITE_PAWN_FIRST_RANK = 1;
    private static final int BLACK_PAWN_FIRST_RANK = 6;

    @Override
    public void validateDirection(final Position source, final Position target, final Color sourceColor,
                                  final boolean doesTargetPositionHavePiece) {
        if (isNotMoveFront(source, target)) {
            validateMoveDiagonal(doesTargetPositionHavePiece, source, target);
            return;
        }
        validateAlreadyExistFront(doesTargetPositionHavePiece);
        judgeColor(sourceColor, source, target);
    }

    private void judgeColor(final Color movablePieceColor, final Position movablePiecePosition,
                            final Position targetPosition) {
        if (isWhitePiece(movablePieceColor)) {
            validateWhiteMoveFront(movablePiecePosition, targetPosition);
        }

        if (isBlackPiece(movablePieceColor)) {
            validateBlackMoveFront(movablePiecePosition, targetPosition);
        }
    }

    private void validateWhiteMoveFront(final Position movablePiecePosition, final Position targetPosition) {
        if (!canMoveWhitePosition(movablePiecePosition, targetPosition)) {
            throw new IllegalArgumentException("이동할 수 없는 블랙 폰입니다.");
        }
    }

    private void validateBlackMoveFront(final Position movablePiecePosition, final Position targetPosition) {
        if (!canMoveBlackPosition(movablePiecePosition, targetPosition)) {
            throw new IllegalArgumentException("이동할 수 없는 블랙 폰입니다.");
        }
    }

    private boolean isNotMoveFront(final Position movablePiecePosition, final Position targetPosition) {
        return isNotSameFile(movablePiecePosition, targetPosition);
    }

    private void validateMoveDiagonal(final boolean doesTargetPositionHavePiece, final Position movablePiecePosition,
                                      final Position targetPosition) {
        if (!doesTargetPositionHavePiece || isNotDiagonal(movablePiecePosition, targetPosition)) {
            throw new IllegalArgumentException("대각선으로 이동할 수 없습니다.");
        }
    }

    private boolean isNotDiagonal(final Position movablePiecePosition, final Position targetPosition) {
        int rankDistance = targetPosition.getRankValue() - movablePiecePosition.getRankValue();
        int fileDistance = targetPosition.getFileValue() - movablePiecePosition.getFileValue();
        return Math.abs(rankDistance) != 1 || Math.abs(fileDistance) != 1;
    }

    private void validateAlreadyExistFront(final boolean doesTargetPositionHavePiece) {
        if (doesTargetPositionHavePiece) {
            throw new IllegalArgumentException("전진하려는 칸에 기물이 존재합니다.");
        }
    }

    private boolean canMoveWhitePosition(final Position movablePiecePosition, final Position targetPosition) {
        validateWhiteMoveBackWard(movablePiecePosition, targetPosition);
        return moveDistanceIsTwoStep(WHITE, movablePiecePosition, targetPosition);
    }

    private boolean canMoveBlackPosition(final Position movablePiecePosition, final Position targetPosition) {
        validateBlackMoveBackWard(movablePiecePosition, targetPosition);
        return moveDistanceIsTwoStep(BLACK, movablePiecePosition, targetPosition);
    }

    private boolean moveDistanceIsTwoStep(final Color color, final Position movablePiecePosition,
                                          final Position targetPosition) {
        int rankDistance = getRankDistance(targetPosition.getRankValue(), movablePiecePosition.getRankValue());
        if (rankDistance == 2) {
            return canMoveTwoStep(color, movablePiecePosition);
        }
        if (rankDistance > 2) {
            throw new IllegalArgumentException("폰은 세 칸 이상 움직일 수 없습니다.");
        }
        return true;
    }

    private boolean canMoveTwoStep(final Color color, final Position movablePiecePosition) {
        if (isWhitePiece(color)) {
            return validateWhiteFirstStep(movablePiecePosition.getRankValue());
        }
        if (isBlackPiece(color)) {
            return validateBlackFirstStep(movablePiecePosition.getRankValue());
        }
        throw new IllegalArgumentException("폰은 첫번째 칸 일 때만 2칸 이동할 수 있습니다.");
    }

    private static void validateWhiteMoveBackWard(final Position movablePiecePosition,
                                                  final Position targetPosition) {
        if (targetPosition.getRankValue() - movablePiecePosition.getRankValue() < 0) {
            throw new IllegalArgumentException("폰은 뒤로 이동할 수 없습니다.");
        }
    }

    private static void validateBlackMoveBackWard(final Position movablePiecePosition,
                                                  final Position targetPosition) {
        if (targetPosition.getRankValue() - movablePiecePosition.getRankValue() > 0) {
            throw new IllegalArgumentException("폰은 뒤로 이동할 수 없습니다.");
        }
    }

    private boolean validateWhiteFirstStep(final int rank) {
        if (rank == WHITE_PAWN_FIRST_RANK) {
            return true;
        }
        throw new IllegalArgumentException("폰은 첫번째 칸 일 때만 2칸 이동할 수 있습니다.");
    }

    private boolean validateBlackFirstStep(final int rank) {
        if (rank == BLACK_PAWN_FIRST_RANK) {
            return true;
        }
        throw new IllegalArgumentException("폰은 첫번째 칸 일 때만 2칸 이동할 수 있습니다.");
    }

    private int getRankDistance(final int targetRank, final int movableRank) {
        return Math.abs(targetRank - movableRank);
    }

    private boolean isNotSameFile(final Position movablePiecePosition, final Position targetPosition) {
        return movablePiecePosition.getFileValue() != targetPosition.getFileValue();
    }

    private boolean isWhitePiece(final Color color) {
        return color == Color.WHITE;
    }

    private boolean isBlackPiece(final Color color) {
        return color == Color.BLACK;
    }

}
