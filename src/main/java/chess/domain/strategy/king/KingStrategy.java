package chess.domain.strategy.king;

import chess.dto.PositionDto;
import chess.dto.request.MoveRequest;
import chess.domain.strategy.PieceStrategy;

public class KingStrategy implements PieceStrategy {

    @Override
    public void validateDirection(final MoveRequest request) {
        PositionDto movablePiecePosition = request.getMovablePieceDto();
        PositionDto targetPosition = request.getTargetPositionDto();

        int fileDistance = Math.abs(movablePiecePosition.getFile() - targetPosition.getFile());
        int rankDistance = Math.abs(movablePiecePosition.getRank() - targetPosition.getRank());

        if (fileDistance > 1 || rankDistance > 1) {
            throw new IllegalArgumentException("킹은 한 칸 이상 이동할 수 없습니다.");
        }
    }

}
