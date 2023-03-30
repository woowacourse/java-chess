package chess.strategy;

import chess.domain.board.Position;

public class QueenStrategy implements MoveStrategy{

    private final CrossStrategy crossStrategy = new CrossStrategy();
    private final DiagonalStrategy diagonalStrategy = new DiagonalStrategy();

    @Override
    public boolean isMovable(Position source, Position target) {
        return crossStrategy.isMovable(source, target) || diagonalStrategy.isMovable(source, target);
    }
}
