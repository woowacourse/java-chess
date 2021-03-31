package chess.domain.piece;

import chess.domain.location.Position;

import java.util.List;

public abstract class Basis implements Piece {
    private final String displayName;

    public Basis(String displayName) {
        this.displayName = displayName;
    }

    public abstract boolean isSameColor(Color color);

    public abstract boolean isEmpty();

    public abstract boolean isKing();


    public abstract double score();


    public abstract boolean isPawn();

    public abstract List<List<Position>> movablePositions(Position position);

    public abstract List<List<Position>> killablePositions(Position position);

    @Override
    public String display() {
        return displayName;
    }
}
