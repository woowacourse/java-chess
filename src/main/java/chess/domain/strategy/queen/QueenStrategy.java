package chess.domain.strategy.queen;

import chess.dto.request.MoveRequest;
import chess.domain.strategy.PieceStrategy;
import chess.domain.strategy.bishop.BishopStrategy;
import chess.domain.strategy.rook.RookStrategy;


public class QueenStrategy implements PieceStrategy {

    private final RookStrategy rookStrategy = new RookStrategy();
    private final BishopStrategy bishopStrategy = new BishopStrategy();

    @Override
    public void validateDirection(final MoveRequest request) {
        try {
            rookStrategy.validateDirection(request);
        } catch (IllegalArgumentException ignored) {
            validateBishopStrategy(request);
        }
    }

    private void validateBishopStrategy(final MoveRequest request) {
        try {
            bishopStrategy.validateDirection(request);
        } catch (IllegalArgumentException queen) {
            throw new IllegalArgumentException("퀸의 이동 경로가 아닙니다.");
        }
    }

}
