package chess.domain.piece.strategy;

import chess.domain.board.Position;
import chess.domain.piece.Color;

public interface MoveStrategy {

    void canMove(Color color, Position from, Position to);
}
