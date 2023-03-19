package chess.strategy;

import chess.domain.Position;

public class QueenStrategy implements MoveStrategy{

    private final CrossStrategy crossStrategy = new CrossStrategy();
    private final DiagonalStrategy diagonalStrategy = new DiagonalStrategy();

    @Override
    public boolean canMove(Position source, Position target) {
        return crossStrategy.canMove(source, target) || diagonalStrategy.canMove(source, target);
    }
}
