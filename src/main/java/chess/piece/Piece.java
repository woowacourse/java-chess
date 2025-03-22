package chess.piece;

import chess.Board;
import chess.Color;
import chess.Position;
import java.util.Set;

public abstract class Piece {
    protected final Board board;
    private Position position;
    private final Color color;

    protected Piece(Position position, Board board, Color color) {
        this.board = board;
        this.position = position;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public void move(Position position) {
        Set<Position> positions = getMovablePositions();
        if (!positions.contains(position)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        board.removeByPosition(position);
        this.position = position;
    }

    protected abstract Set<Position> getMovablePositions();
}
