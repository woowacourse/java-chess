package chess.domain.strategy.bishop;

import chess.domain.strategy.PieceStrategy;
import chess.dto.PositionDto;
import chess.dto.request.MoveRequest;

public class BishopStrategy implements PieceStrategy {

    @Override
    public void validateDirection(final MoveRequest request) {
        PositionDto movablePiecePosition = request.getMovablePieceDto();
        PositionDto targetPosition = request.getTargetPositionDto();

        int fileDistance = Math.abs(movablePiecePosition.getFile() - targetPosition.getFile());
        int rankDistance = Math.abs(movablePiecePosition.getRank() - targetPosition.getRank());

        if (fileDistance != rankDistance) {
            throw new IllegalArgumentException("대각선 방향이 아닙니다.");
        }
    }

}
