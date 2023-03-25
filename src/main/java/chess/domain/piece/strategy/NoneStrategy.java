package chess.domain.piece.strategy;

import chess.domain.position.Move;

public class NoneStrategy implements MoveStrategy {

    private static final NoneStrategy INSTANCE = new NoneStrategy();

    private NoneStrategy() {
    }

    public static NoneStrategy instance() {
        return INSTANCE;
    }

    @Override
    public boolean canMove(Move move) {
        throw new UnsupportedOperationException("해당 이동 전략을 불러올 수 없습니다.");
    }
}
