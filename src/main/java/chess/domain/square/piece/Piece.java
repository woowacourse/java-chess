package chess.domain.square.piece;

import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.square.Square;

import java.util.Map;

public abstract class Piece implements Square {
    public final Color color;

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
    protected abstract void move();

    @Override
    public boolean canAttack(Path path, Map<Position, Square> board) {
        return isValidAttackPath(path) && isNotObstructed(path, board) && isEnemyAttack(path, board);
    }

    protected abstract boolean isValidAttackPath(Path path);

    private final boolean isEnemyAttack(Path path, Map<Position, Square> board) {
        Piece endPiece = (Piece) board.get(path.getEnd());

        return color != endPiece.color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                '}';
    }
}
