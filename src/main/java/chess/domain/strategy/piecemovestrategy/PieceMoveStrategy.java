package chess.domain.strategy.piecemovestrategy;

import chess.domain.position.Position;

public interface PieceMoveStrategy {

    boolean isMovable(Position from, Position to);

    PieceType getPieceType();
}
