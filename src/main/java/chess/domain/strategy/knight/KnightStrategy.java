package chess.domain.strategy.knight;

import chess.domain.dto.PositionDto;
import chess.domain.dto.req.MoveRequest;
import chess.domain.strategy.PieceStrategy;

public class KnightStrategy implements PieceStrategy {

    public void validateDirection(final MoveRequest request) {
        PositionDto movablePiecePosition = request.getMovablePieceDto();
        PositionDto targetPosition = request.getTargetPositionDto();

        int rankDistance = Math.abs(movablePiecePosition.getRank() - targetPosition.getRank());
        int fileDistance = Math.abs(movablePiecePosition.getFile() - targetPosition.getFile());

        if (!isValid(rankDistance, fileDistance)) {
            throw new IllegalArgumentException("나이트가 움직일 수 있는 곳이 아닙니다.");
        }
    }

    boolean isValid(final int rankDistance, final int fileDistance) {
        return rankDistance == 1 && fileDistance == 2
                || rankDistance == 2 && fileDistance == 1;
    }

}
