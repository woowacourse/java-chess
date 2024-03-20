package chess.domain.strategy;

import chess.domain.Color;
import chess.domain.Piece;
import chess.domain.Position;

public interface MoveStrategy {
    MoveStrategy changeStrategy(Position from);

    void move(Color turnColor, Position from, Position to);

    default void checkTurnOf(Piece currentPiece, Color turnColor) {
        if (!currentPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("상대 말은 이동할 수 없습니다.");
        }
    }
}
