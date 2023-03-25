package chess.domain.piece.strategy;

import chess.domain.position.Move;

public class KnightStrategy implements MoveStrategy {

    private static final KnightStrategy INSTANCE = new KnightStrategy();
    private static final int PRODUCT_OF_CHANGE = 2;

    private KnightStrategy() {
    }

    public static KnightStrategy instance() {
        return INSTANCE;
    }

    @Override
    public boolean canMove(Move move) {
        return Math.abs(move.getDeltaFile() * move.getDeltaRank()) == PRODUCT_OF_CHANGE;
    }
}
