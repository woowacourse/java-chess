package chess.domain.piece;

import java.util.List;

import chess.domain.piece.exception.NotMovableException;

public abstract class Piece {
    private Position position;
    private Color color;

    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public void move(Position position) {
        this.position = position;
    }

    public final boolean isBlack() {
        return color.isBlack();
    }

    public final Path pathTo(Piece piece) {
        validateColor(piece);
        int dx = piece.position.getX() - position.getX();
        int dy = piece.position.getY() - position.getY();
        Direction direction = findDirection(dx, dy);
        validateDirections(direction, movableDirections(piece, direction));
        return new Path(position, piece.position, direction);
    }

    private void validateColor(Piece piece) {
        if (piece.isSameColor(this)) {
            throw new NotMovableException("목표 위치에 같은 색상의 말이 존재하여 이동할 수 없습니다.");
        }
    }

    private void validateDirections(Direction direction, List<Direction> directions) {
        if (!directions.contains(direction)) {
            throw new NotMovableException();
        }
    }

    public final boolean isSameColor(Piece piece) {
        return color == piece.color;
    }

    public final boolean isSameColor(Color color) {
        return this.color == color;
    }

    protected abstract List<Direction> movableDirections(Piece piece, Direction direction);

    protected abstract Direction findDirection(int x, int y);
}
