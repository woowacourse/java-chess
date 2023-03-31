package chess.domain.piece.strategy;

import chess.domain.position.Move;

public class DiagonalStrategy implements MoveStrategy {

    private static final DiagonalStrategy INSTANCE = new DiagonalStrategy();

    private DiagonalStrategy() {
    }

    public static DiagonalStrategy instance() {
        return INSTANCE;
    }

    @Override
    public boolean canMove(Move move) {
        return move.isDiagonal();
    }
}
