package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public abstract class Division extends Basis {
    protected final Color color;

    public Division(Color color, String displayName) {
        super(displayName);
        this.color = color;
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

    public abstract double score();

    public abstract boolean isPawn();

    public abstract List<List<Position>> movablePositions(Position position);

    public abstract List<List<Position>> killablePositions(Position position);
}
