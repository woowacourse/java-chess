package chess.domain.strategy;

import chess.domain.Color;
import chess.domain.Position;

public interface PieceStrategy {

    void validateDirection(final Position source, final Position target, final Color sourceColor,
                           final boolean doesTargetPositionHavePiece);

}
