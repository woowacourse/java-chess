package chess.domain.square.piece;

import chess.domain.position.Position;
import chess.domain.position.TerminalPosition;
import chess.domain.square.Square;
import chess.domain.square.piece.movement.Movements;

import java.util.List;
import java.util.Objects;

public abstract class Piece implements Square {
    private final Color color;
    private final Movements movements;

    public Piece(Color color, Movements movements) {
        this.color = color;
        this.movements = movements;
    }

    @Override
    public final List<Position> findPassPathTaken(TerminalPosition terminalPosition) {
        return movements.findPassPathTaken(terminalPosition, maxPassMoveCount());
    }

    protected abstract int maxPassMoveCount();

    @Override
    public final List<Position> findAttackPathTaken(TerminalPosition terminalPosition) {
        return movements.findAttackPathTaken(terminalPosition, maxAttackMoveCount());
    }

    protected abstract int maxAttackMoveCount();

    // TODO: Pawn이 움직인 적이 있는지 확인하는 로직 개선
    public abstract void move();

    public boolean isColor(Color color) {
        return this.color == color;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(color);
    }

    public Color getColor() {
        return color;
    }
}
