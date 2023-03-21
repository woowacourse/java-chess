package chess.domain.strategy.pawn;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.dto.PositionDto;
import chess.domain.dto.req.MoveRequest;
import chess.domain.strategy.PieceStrategy;

import java.util.List;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;

public class PawnStrategy implements PieceStrategy {

    private static final int WHITE_PAWN_FIRST_RANK = 1;
    private static final int BLACK_PAWN_FIRST_RANK = 6;

    @Override
    public void validateDirection(final MoveRequest request) {
        PositionDto movablePiecePosition = request.getMovablePieceDto();
        PositionDto targetPosition = request.getTargetPositionDto();
        List<Position> positions = request.getPositions();

        if (isNotMoveFront(movablePiecePosition, targetPosition)) {
            validateMoveDiagonal(positions, movablePiecePosition, targetPosition);
            return;
        }
        validateAlreadyExistFront(positions, targetPosition);
        judgeColor(request.getMovablePieceColor(), movablePiecePosition, targetPosition);
    }

    private void judgeColor(Color movablePieceColor, PositionDto movablePiecePosition, PositionDto targetPosition) {
        if (isWhitePiece(movablePieceColor)) {
            validateWhiteMoveFront(movablePiecePosition, targetPosition);
        }

        if (isBlackPiece(movablePieceColor)) {
            validateBlackMoveFront(movablePiecePosition, targetPosition);
        }
    }

    private void validateWhiteMoveFront(PositionDto movablePiecePosition, PositionDto targetPosition) {
        if (!canMoveWhitePosition(movablePiecePosition, targetPosition)) {
            throw new IllegalArgumentException("이동할 수 없는 블랙 폰입니다.");
        }
    }

    private void validateBlackMoveFront(PositionDto movablePiecePosition, PositionDto targetPosition) {
        if (!canMoveBlackPosition(movablePiecePosition, targetPosition)) {
            throw new IllegalArgumentException("이동할 수 없는 블랙 폰입니다.");
        }
    }

    private boolean isNotMoveFront(PositionDto movablePiecePosition, PositionDto targetPosition) {
        return isNotSameFile(movablePiecePosition, targetPosition);
    }

    private void validateMoveDiagonal(List<Position> positions, PositionDto movablePiecePosition, PositionDto targetPosition) {
        boolean isNotExistPosition = !positions.contains(Position.from(targetPosition.getRank(), targetPosition.getFile()));
        if (isNotExistPosition || isNotDiagonal(movablePiecePosition, targetPosition)) {
            throw new IllegalArgumentException("대각선으로 이동할 수 없습니다.");
        }
    }

    private boolean isNotDiagonal(PositionDto movablePiecePosition, PositionDto targetPosition) {
        int rankDistance = targetPosition.getRank() - movablePiecePosition.getRank();
        int fileDistance = targetPosition.getFile() - movablePiecePosition.getFile();
        return Math.abs(rankDistance) != 1 || Math.abs(fileDistance) != 1;
    }

    private void validateAlreadyExistFront(List<Position> positions, PositionDto targetPosition) {
        boolean isAlreadyExistTargetPosition = positions.contains(Position.from(targetPosition.getRank(), targetPosition.getFile()));
        if (isAlreadyExistTargetPosition) {
            throw new IllegalArgumentException("전진하려는 칸에 기물이 존재합니다.");
        }
    }

    private boolean canMoveWhitePosition(PositionDto movablePiecePosition, PositionDto targetPosition) {
        validateWhiteMoveBackWard(movablePiecePosition, targetPosition);
        return moveDistanceIsTwoStep(WHITE, movablePiecePosition, targetPosition);
    }

    private boolean canMoveBlackPosition(PositionDto movablePiecePosition, PositionDto targetPosition) {
        validateBlackMoveBackWard(movablePiecePosition, targetPosition);
        return moveDistanceIsTwoStep(BLACK, movablePiecePosition, targetPosition);
    }

    private boolean moveDistanceIsTwoStep(Color color, PositionDto movablePiecePosition, PositionDto targetPosition) {
        int rankDistance = getRankDistance(targetPosition.getRank(), movablePiecePosition.getRank());
        if (rankDistance == 2) {
            return canMoveTwoStep(color, movablePiecePosition);
        }
        if (rankDistance > 2) {
            throw new IllegalArgumentException("폰은 세 칸 이상 움직일 수 없습니다.");
        }
        return true;
    }

    private boolean canMoveTwoStep(Color color, PositionDto movablePiecePosition) {
        if (isWhitePiece(color)) {
            return validateWhiteFirstStep(movablePiecePosition.getRank());
        }
        if (isBlackPiece(color)) {
            return validateBlackFirstStep(movablePiecePosition.getRank());
        }
        throw new IllegalArgumentException("폰은 첫번째 칸 일 때만 2칸 이동할 수 있습니다.");
    }

    private static void validateWhiteMoveBackWard(PositionDto movablePiecePosition, PositionDto targetPosition) {
        if (targetPosition.getRank() - movablePiecePosition.getRank() < 0) {
            throw new IllegalArgumentException("폰은 뒤로 이동할 수 없습니다.");
        }
    }

    private static void validateBlackMoveBackWard(PositionDto movablePiecePosition, PositionDto targetPosition) {
        if (targetPosition.getRank() - movablePiecePosition.getRank() > 0) {
            throw new IllegalArgumentException("폰은 뒤로 이동할 수 없습니다.");
        }
    }

    private boolean validateWhiteFirstStep(int rank) {
        if (rank == WHITE_PAWN_FIRST_RANK) {
            return true;
        }
        throw new IllegalArgumentException("폰은 첫번째 칸 일 때만 2칸 이동할 수 있습니다.");
    }

    private boolean validateBlackFirstStep(int rank) {
        if (rank == BLACK_PAWN_FIRST_RANK) {
            return true;
        }
        throw new IllegalArgumentException("폰은 첫번째 칸 일 때만 2칸 이동할 수 있습니다.");
    }

    private int getRankDistance(int targetRank, int movableRank) {
        return Math.abs(targetRank - movableRank);
    }

    private boolean isNotSameFile(PositionDto movablePiecePosition, PositionDto targetPosition) {
        return movablePiecePosition.getFile() != targetPosition.getFile();
    }

    private boolean isWhitePiece(Color color) {
        return color.isWhite();
    }

    private boolean isBlackPiece(Color color) {
        return color.isBlack();
    }


}
