package chess.domain.piece.strategy;

import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;

public class VoidMoveStrategy implements MoveStrategy {

    private static final String VOID_MESSAGE = "빈 칸입니다.";

    @Override
    public void move(Position source, Position target) {
        throw new InvalidMoveException(VOID_MESSAGE);
    }
}
