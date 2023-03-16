package chess.domain.strategy.pawn;

import chess.domain.Position;
import chess.domain.dto.req.MoveRequest;

public interface PieceStrategy {

    Position move(final MoveRequest request);

}
