package chess.domain.piece;

import chess.domain.location.Position;
import chess.domain.moveStrategy.MoveStrategy;

import java.util.List;
import java.util.Map;

public abstract class Division extends Basis {
    protected final Color color;
    private final double score;
    private final MoveStrategy moveStrategy;

    public Division(Color color, double score, char displayName, MoveStrategy moveStrategy) {
        super(displayName);
        this.score = score;
        this.color = color;
        this.moveStrategy = moveStrategy;
    }

    @Override
    public boolean isSame(Color color) {
        return this.color.equals(color);
    }

    @Override
    public boolean isOpposite(Color color) {
        return !isSame(color);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public double score() {
        return score;
    }

    @Override
    public List<Position> movablePositions(Position from, Map<Position, Piece> pieceByPosition) {
        return moveStrategy.movablePositions(from, pieceByPosition);
    }

    public abstract boolean isPawn();

    public abstract boolean isKing();

}
