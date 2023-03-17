package chess.domain.strategy.pawn;

import chess.domain.Position;
import chess.domain.dto.PositionDto;
import chess.domain.dto.req.MoveRequest;
import chess.domain.strategy.PieceStrategy;

import java.util.List;

public class PawnStrategy implements PieceStrategy {

    @Override
    public void validateDirection(final MoveRequest request) {
        List<Position> positions = request.getPositions();
        String movablePieceColor = request.getMovablePieceColor();
        PositionDto movablePiecePosition = request.getMovablePieceDto();
        PositionDto targetPosition = request.getTargetPositionDto();

        boolean moveFrontPosition = canMoveFront(positions, movablePieceColor, movablePiecePosition, targetPosition);
        if (moveFrontPosition) {
            return;
        }

        boolean moveDiagonalPosition = canMoveDiagonal(movablePiecePosition, targetPosition);
        if (moveDiagonalPosition) {
            return;
        }
        throw new IllegalArgumentException("이동할 수 있는 위치가 아닙니다.");
    }

    private boolean canMoveFront(List<Position> positions, String movablePieceColor, PositionDto movablePiecePosition, PositionDto targetPosition) {
        if (isNotAlreadyExistFront(positions, targetPosition) && isSameFile(movablePiecePosition, targetPosition)) {
            if (isWhitePiece(movablePieceColor)) {
                return getWhitePosition(movablePiecePosition, targetPosition);
            }
            return getBlackPosition(movablePiecePosition, targetPosition);
        }
        return false;
    }

    private boolean canMoveDiagonal(PositionDto movablePiecePosition, PositionDto targetPosition) {
        if (isDiagonal(movablePiecePosition, targetPosition)) {
            return true;
        }
        throw new IllegalArgumentException("이동할 수 있는 위치가 아닙니다.");
    }

    private boolean isDiagonal(PositionDto movablePiecePosition, PositionDto targetPosition) {
        int rankDistance = targetPosition.getRank() - movablePiecePosition.getRank();
        int fileDistance = targetPosition.getFile() - movablePiecePosition.getFile();
        return Math.abs(rankDistance) == 1 && Math.abs(fileDistance) == 1;
    }

    private boolean isNotAlreadyExistFront(List<Position> positions, PositionDto targetPosition) {
        return positions.stream()
                .anyMatch(position -> !position.isSamePosition(Position.from(targetPosition.getRank(), targetPosition.getFile())));
    }

    private boolean getWhitePosition(PositionDto movablePiecePosition, PositionDto targetPosition) {
        validateWhiteMoveBackWard(movablePiecePosition, targetPosition);
        if (targetPosition.getRank() - movablePiecePosition.getRank() <= 2) {
            if (isWhiteFirstStep(movablePiecePosition.getRank())) {
                return true;
            }
            throw new IllegalArgumentException("첫번째 칸 일 때만 2칸 이동할 수 있습니다.");
        }
        throw new IllegalArgumentException("첫번째 칸 일 때만 2칸 이동할 수 있습니다.");
    }

    private static void validateWhiteMoveBackWard(PositionDto movablePiecePosition, PositionDto targetPosition) {
        if (targetPosition.getRank() - movablePiecePosition.getRank() < 0) {
            throw new IllegalArgumentException("폰은 뒤로 이동할 수 없습니다.");
        }
    }

    private boolean getBlackPosition(PositionDto movablePiecePosition, PositionDto targetPosition) {
        validateBlackMoveBackWard(movablePiecePosition, targetPosition);
        if (targetPosition.getRank() - movablePiecePosition.getRank() == -2) {
            if (isBlackFirstStep(movablePiecePosition.getRank())) {
                return true;
            }
            throw new IllegalArgumentException("첫번째 칸 일 때만 2칸 이동할 수 있습니다.");
        }
        throw new IllegalArgumentException("첫번째 칸 일 때만 2칸 이동할 수 있습니다.");
    }

    private static void validateBlackMoveBackWard(PositionDto movablePiecePosition, PositionDto targetPosition) {
        if (targetPosition.getRank() - movablePiecePosition.getRank() > 0) {
            throw new IllegalArgumentException("폰은 뒤로 이동할 수 없습니다.");
        }
    }

    private boolean isBlackFirstStep(int rank) {
        return rank == 6;
    }

    private boolean isWhitePiece(String movablePieceColor) {
        return movablePieceColor.equals("white");
    }

    private boolean isWhiteFirstStep(int rank) {
        return rank == 1;
    }

    private boolean isSameFile(PositionDto movablePiecePosition, PositionDto targetPosition) {
        return movablePiecePosition.getFile() == targetPosition.getFile();
    }

}
