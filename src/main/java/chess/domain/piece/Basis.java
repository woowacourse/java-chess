package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;

import java.util.List;

public abstract class Basis implements Piece {
    private final String displayName;

    public Basis(String displayName) {
        this.displayName = displayName;
    }

    public abstract void moveToEmpty(Position to, Pieces pieces);

    public abstract void moveForKill(Position to, Pieces pieces);

    public abstract Position getPosition();

    public abstract boolean isSameColor(Color color);
    public abstract boolean isSameColor(Piece piece);

    public abstract boolean isEmpty();


    public abstract boolean isKing();


    public abstract double score();


    public abstract boolean isPawn();

    public abstract Column getColumn();

    public abstract List<Position> movablePositions(Position position);

    public abstract List<Position> killablePositions(Position position);

    @Override
    public String display() {
        return displayName;
    }
}
