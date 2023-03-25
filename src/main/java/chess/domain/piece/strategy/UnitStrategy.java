package chess.domain.piece.strategy;

import chess.domain.position.Move;

public class UnitStrategy implements MoveStrategy {

    private static final UnitStrategy INSTANCE = new UnitStrategy();

    private UnitStrategy() {
    }

    public static UnitStrategy instance() {
        return INSTANCE;
    }

    @Override
    public boolean canMove(Move move) {
        return Math.max(Math.abs(move.getDeltaFile()), Math.abs(move.getDeltaRank())) == 1;
    }
}
