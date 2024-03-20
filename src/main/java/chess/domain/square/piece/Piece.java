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
        return isValidPath(path) && isNotObstructed(path, board);
    }

    protected abstract boolean isValidPath(Path path);

    protected abstract boolean isNotObstructed(Path path, Map<Position, Square> board);

    // TODO: Pawn이 움직인 적이 있는지 확인하는 로직 개선
    protected abstract void move();

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
