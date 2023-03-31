package chess.domain.strategy;

import chess.dto.request.MoveRequest;

public interface PieceStrategy {

    void validateDirection(final MoveRequest request);

}
