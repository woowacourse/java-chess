package chess.domain.strategy.pawn;

import chess.domain.Position;
import chess.domain.dto.PositionDto;
import chess.domain.dto.req.MoveRequest;

import java.util.List;

public class PawnStrategy implements PieceStrategy {

    @Override
    public Position move(final MoveRequest request) {
        List<Position> positions = request.getPositions();
        String movablePieceColor = request.getMovablePieceColor();
        PositionDto movablePiecePosition = request.getMovablePieceDto();
        PositionDto targetPosition = request.getTargetPositionDto();

        if (isNotAlreadyExistFront(positions, targetPosition) && isSameFile(movablePiecePosition, targetPosition)) {
            if (isWhitePiece(movablePieceColor)) {
                return moveFrontWhite(movablePiecePosition, targetPosition);
            }
            return moveFrontBlack(movablePiecePosition, targetPosition);
        }

       return moveDiagonal(movablePieceColor, movablePiecePosition, targetPosition);
    }

    private boolean isNotAlreadyExistFront(List<Position> positions, PositionDto targetPosition) {
        return positions.stream()
                .anyMatch(position -> !position.isSamePosition(Position.from(targetPosition.getRank(), targetPosition.getFile())));
    }

    private Position moveFrontWhite(
            final PositionDto movablePiecePosition,
            final PositionDto targetPosition
    ) {
        return getWhitePosition(movablePiecePosition, targetPosition);
    }

    private Position moveFrontBlack(
            final PositionDto movablePiecePosition,
            final PositionDto targetPosition
    ) {
        return getBlackPosition(
                movablePiecePosition,
                targetPosition
        );
    }

    private Position getWhitePosition(PositionDto movablePiecePosition, PositionDto targetPosition) {
        int rankDistance = targetPosition.getRank() - movablePiecePosition.getRank();

        if (isOneStep(rankDistance)) {
            return Position.from(targetPosition.getRank(), targetPosition.getFile());
        }

        if (isWhiteFirstStep(movablePiecePosition.getRank()) && isTwoStep(rankDistance)) {
            return Position.from(targetPosition.getRank(), targetPosition.getFile());
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
    }

    private Position getBlackPosition(PositionDto movablePiecePosition, PositionDto targetPosition) {
        int rankDistance = movablePiecePosition.getRank() - targetPosition.getRank();
        if (isOneStep(rankDistance)) {
            return Position.from(targetPosition.getRank(), targetPosition.getFile());
        }

        if (isBlackFirstStep(movablePiecePosition.getRank()) && isTwoStep(rankDistance)) {
            return Position.from(targetPosition.getRank(), targetPosition.getFile());
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
    }

    private Position moveDiagonal(
            final String movablePieceColor,
            final PositionDto movablePiecePosition,
            final PositionDto targetPosition
    ) {
        int movablePieceRank = movablePiecePosition.getRank();
        char movablePieceFile = movablePiecePosition.getFile();

        int targetRank = targetPosition.getRank();
        char targetFile = targetPosition.getFile();

        if (movablePieceColor.equals("white")) {
            int rankDistance = targetRank - movablePieceRank;
            int fileDistance = targetFile - movablePieceFile;
            return Position.from(movablePieceRank + rankDistance, (char) (movablePieceFile + fileDistance));
        }

        if (movablePieceColor.equals("black")) {
            int rankDistance = targetRank - movablePieceFile;
            int fileDistance = targetFile - movablePieceFile;
            return Position.from(movablePieceRank - rankDistance, (char) (movablePieceFile - fileDistance));
        }

        throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
    }

    private boolean isBlackFirstStep(int rank) {
        return rank == 7;
    }

    private boolean isWhitePiece(String movablePieceColor) {
        return movablePieceColor.equals("white");
    }

    private boolean isWhiteFirstStep(int rank) {
        return rank == 2;
    }

    private boolean isTwoStep(int distance) {
        return distance == 2;
    }

    private boolean isOneStep(int distance) {
        return distance == 1;
    }

    private boolean isSameFile(PositionDto movablePiecePosition, PositionDto targetPosition) {
        return movablePiecePosition.getFile() == targetPosition.getFile();
    }

}
