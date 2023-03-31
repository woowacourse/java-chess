package chess.domain.model.piece.shape.strategy;

import chess.domain.model.player.Color;
import chess.domain.model.position.Position;

public interface PieceStrategy {

    void validateDirection(final Position source, final Position target, final Color sourceColor,
                           final boolean doesTargetPositionHavePiece);

}
