package chess.domain.strategy;

import chess.domain.dto.request.MoveRequest;

public interface PieceStrategy {

    void validateDirection(final MoveRequest request);

}
