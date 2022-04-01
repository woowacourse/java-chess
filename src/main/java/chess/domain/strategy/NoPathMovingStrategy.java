package chess.domain.strategy;

import chess.domain.ChessBoardPosition;
import java.util.Collections;
import java.util.List;

public class NoPathMovingStrategy implements MovingStrategy {
    @Override
    public List<ChessBoardPosition> makePath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        return Collections.emptyList();
    }
}
