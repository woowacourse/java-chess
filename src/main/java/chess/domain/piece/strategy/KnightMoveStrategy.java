package chess.domain.piece.strategy;

import chess.domain.dto.BoardDto;
import chess.domain.position.Position;

public class KnightMoveStrategy implements MoveStrategy {
    @Override
    public boolean canMove(BoardDto boardDto, Position from, Position to) {
        return false;
    }
}
