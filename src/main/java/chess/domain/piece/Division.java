package chess.domain.piece;

import chess.domain.location.Position;
import chess.domain.moveStrategy.MoveStrategy;

import java.util.List;
import java.util.Map;

public abstract class Division extends Basis {
    protected final Color color;
    private final MoveStrategy moveStrategy;

    public Division(Color color, char displayName, MoveStrategy moveStrategy) {
        super(displayName);
        this.color = color;
        this.moveStrategy = moveStrategy;
    }

    @Override
    public String display() {
        if (Color.BLACK.equals(color)) {
            return super.display()
                        .toUpperCase();
        }
        return super.display();
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

    public abstract boolean isKing();

    public abstract boolean isPawn();

    public abstract double score();

    public List<Position> movablePositions(Position from, Map<Position, Piece> pieceByPosition) {
        return moveStrategy.movablePositions(from, pieceByPosition);
    }

}
