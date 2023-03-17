package chess.domain.strategy.queen;

import chess.domain.dto.req.MoveRequest;
import chess.domain.strategy.PieceStrategy;
import chess.domain.strategy.bishop.BishopStrategy;
import chess.domain.strategy.rook.RookStrategy;


public class QueenStrategy implements PieceStrategy {

    private final RookStrategy rookStrategy = new RookStrategy();
    private final BishopStrategy bishopStrategy = new BishopStrategy();

    @Override
    public void validateDirection(MoveRequest request) {
        try {
            rookStrategy.validateDirection(request);
        } catch (IllegalArgumentException ignored) {
            bishopStrategy.validateDirection(request);
        }
    }

}
