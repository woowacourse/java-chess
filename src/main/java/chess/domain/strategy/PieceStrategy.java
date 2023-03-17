package chess.domain.strategy;

import chess.domain.dto.req.MoveRequest;

public interface PieceStrategy {

    void validateDirection(final MoveRequest request);

}
