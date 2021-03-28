package chess.domain.piece.strategy;

import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;

public class VoidMoveStrategy implements MoveStrategy {

    public static final String VOID_MESSAGE = "빈 칸입니다.";

    @Override
    public void move(final Position source, final Position target) {
        throw new InvalidMoveException(VOID_MESSAGE);
    }
}
