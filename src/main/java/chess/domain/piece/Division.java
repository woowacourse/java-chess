package chess.domain.piece;

import chess.domain.location.Position;
import chess.domain.moveStrategy.MoveStrategy;

import java.util.List;

public abstract class Division extends Basis {
    protected final Color color;
    private final MoveStrategy moveStrategy;
    private final MoveStrategy killStrategy;

    public Division(Color color, String displayName, MoveStrategy moveStrategy, MoveStrategy killStrategy) {
        super(displayName);
        this.color = color;
        this.moveStrategy = moveStrategy;
        this.killStrategy = killStrategy;
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
    public boolean isSameColor(Color color) {
        return this.color.equals(color);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public abstract boolean isKing();

    public abstract boolean isPawn();

    public abstract double score();

    public List<List<Position>> movablePositions(Position position) {
        return moveStrategy.movablePositions(position);
    }

    public List<List<Position>> killablePositions(Position position) {
        return killStrategy.movablePositions(position);
    }
}
