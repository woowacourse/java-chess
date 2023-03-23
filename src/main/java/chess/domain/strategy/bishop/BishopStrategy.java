package chess.domain.strategy.bishop;

import chess.domain.dto.PositionDto;
import chess.domain.dto.req.MoveRequest;
import chess.domain.strategy.PieceStrategy;

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
