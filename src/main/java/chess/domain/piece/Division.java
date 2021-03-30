package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.List;

public abstract class Division extends Basis {
    protected final Color color;
    protected Position position;

    public Division(Color color, String displayName, Position position) {
        super(displayName);
        this.color = color;
        this.position = position;
    }

    protected Row initPawnRow() {
        return color.initPawnRow();
    }

    public abstract void moveToEmpty(Position to, Pieces pieces);

    public abstract void moveForKill(Position to, Pieces pieces);

    @Override
    public Position getPosition() {
        return position;
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
    public boolean hasPosition(Position position) {
        return this.position.equals(position);
    }

    @Override
    public boolean isSameColor(Color color) {
        return this.color.equals(color);
    }

    public boolean isSameColor(Piece piece) {
        return piece.isSameColor(this.color);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public abstract boolean isKing();


    public abstract double score();


    public abstract boolean isPawn();

    @Override
    public Column getColumn() {
        return position.column();
    }

    public abstract List<Position> movablePositions(Position position);

    public abstract List<Position> killablePositions(Position position);
}
