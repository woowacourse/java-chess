package chess.domain.piece.strategy;

import chess.domain.position.Move;

public class StraightStrategy implements MoveStrategy {

    private static final StraightStrategy INSTANCE = new StraightStrategy();

    private StraightStrategy() {
    }

    public static StraightStrategy instance() {
        return INSTANCE;
    }

    @Override
    public boolean canMove(Move move) {
        return move.isStraight();
    }
}
