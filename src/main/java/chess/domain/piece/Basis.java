package chess.domain.piece;

import chess.domain.location.Position;

import java.util.List;
import java.util.Map;

public abstract class Basis implements Piece {
    protected final char displayName;

    public Basis(char displayName) {
        this.displayName = displayName;
    }

    public abstract boolean isSame(Color color);

    public abstract boolean isOpposite(Color color);

    public abstract boolean isEmpty();

    public abstract boolean isKing();

    public abstract boolean isPawn();

    public abstract double score();

    public abstract List<Position> movablePositions(Position from, Map<Position, Piece> pieceByPosition);


    @Override
    public String display() {
        return String.valueOf(displayName);
    }
}
