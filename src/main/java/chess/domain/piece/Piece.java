package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

import java.util.List;

public abstract class Piece {
    protected final Color color;
    protected int moveCount;

    public Piece(Color color) {
        this.color = color;
        this.moveCount = 0;
    }

    public String signature() {
        return color.correctSignature(baseSignature());
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isNeverDisplaced() {
        return moveCount == 0;
    }

    public Piece displaced() {
        this.moveCount++;
        return this;
    }

    public boolean isBlank(){
        return false;
    }

    public boolean isPawn(){
        return false;
    }

    public boolean isKing(){
        return false;
    }

    public boolean isRook(){
        return false;
    }

    public boolean isEnPassantAvailable(){
        return false;
    }

    protected abstract String baseSignature();

    public abstract boolean isMovable(Position source, Position target);

    public abstract List<Position> findRoute(Position source, Position target);

    public abstract double score();
}
