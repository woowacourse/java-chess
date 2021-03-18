package chess.domain.piece.strategy;

import chess.domain.dto.BoardDto;
import chess.domain.position.Position;

public interface MoveStrategy {
    boolean canMove(BoardDto boardDto, Position from, Position to);
}
