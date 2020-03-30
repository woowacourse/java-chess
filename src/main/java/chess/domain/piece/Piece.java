package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

import chess.domain.piece.exception.NotMovableException;

public abstract class Piece {
    private Position position;
    private Color color;
    private Symbol symbol;
    private final List<MoveEventListener> moveEventListeners;

    public Piece(Position position, Color color, Symbol symbol) {
        this.position = position;
        this.color = color;
        this.symbol = symbol;
        this.moveEventListeners = new ArrayList<>();
    }

    public double score() {
        return symbol.getScore();
    }

    public String symbol() {
        return symbol.getName();
    }

    public void move(Piece targetPiece) {
        Path pathToTarget = pathTo(targetPiece);
        notifyMoveEventListeners(new MoveEvent(this.position, targetPiece.position, pathToTarget));
        position = targetPiece.position;
    }

    public final void onMoveEvent(MoveEventListener moveEventListener) {
        moveEventListeners.add(moveEventListener);
    }

    private void notifyMoveEventListeners(MoveEvent moveEvent) {
        for (MoveEventListener moveEventListener : moveEventListeners) {
            moveEventListener.call(moveEvent);
        }
    }

    public final boolean isBlack() {
        return color.isBlack();
    }

    public final Path pathTo(Piece piece) {
        validateColor(piece);
        RelativePosition relativePosition = RelativePosition.of(position, piece.position);
        Direction direction = findDirection(relativePosition.getX(), relativePosition.getY());
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

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isBlank() {
        return false;
    }
}
