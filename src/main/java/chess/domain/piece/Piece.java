package chess.domain.piece;

import chess.domain.movement.Movements;
import chess.domain.position.Position;
import chess.domain.position.TerminalPosition;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final Color color;
    private final Movements movements;

    public Piece(Color color, Movements movements) {
        this.color = color;
        this.movements = movements;
    }

    public final List<Position> findPassPathTaken(TerminalPosition terminalPosition) {
        return movements.findPassPathTaken(terminalPosition, maxPassMoveCount());
    }

    protected abstract int maxPassMoveCount();

    public final List<Position> findAttackPathTaken(TerminalPosition terminalPosition) {
        return movements.findAttackPathTaken(terminalPosition, maxAttackMoveCount());
    }

    protected abstract int maxAttackMoveCount();

    /**
     * @ImplSpec 각 Piece의 움직임을 추적해야 하는 경우, move 메소드를 적절히 오버라이딩하여 사용할 수 있다. 기본적인 동작은 아무것도 수행하지 않는 것이다.
     */
    public void move() {
    }

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
