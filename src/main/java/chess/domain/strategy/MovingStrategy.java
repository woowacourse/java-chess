package chess.domain.strategy;

import chess.domain.ChessBoardPosition;
import java.util.List;

public interface MovingStrategy {

    List<ChessBoardPosition> makePath(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition);

    boolean isKillMovement(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition);
}
