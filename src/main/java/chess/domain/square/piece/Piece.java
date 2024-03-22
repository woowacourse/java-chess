package chess.domain.square.piece;

import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.square.Square;

import java.util.Map;
import java.util.Objects;

public abstract class Piece implements Square {
    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    @Override
    public final boolean canMove(Path path, Map<Position, Square> board) {
        return isValidMovePath(path) && isNotObstructed(path, board);
    }

    protected abstract boolean isValidMovePath(Path path);

    protected abstract boolean isNotObstructed(Path path, Map<Position, Square> board);

    // TODO: Pawn이 움직인 적이 있는지 확인하는 로직 개선
    public abstract void move();

    @Override
    public boolean canAttack(Path path, Map<Position, Square> board) {
        return isValidAttackPath(path) && isNotObstructed(path, board) && isEnemyAttack(path, board);
    }

    protected abstract boolean isValidAttackPath(Path path);

    // TODO: 하위 타입 캐스팅 대신 Map<Position, Piece>로 변경할지 고려
    // TODO: 체스보드가 검증하도록 변경
    private boolean isEnemyAttack(Path path, Map<Position, Square> board) {
        Piece endPiece = (Piece) board.get(path.getTargetPosition());

        return color != endPiece.color;
    }

    @Override
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
