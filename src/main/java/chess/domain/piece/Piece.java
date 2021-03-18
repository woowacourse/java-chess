package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;

public abstract class Piece {
    private Position position;
    private final boolean isBlack;

    public Piece(final boolean isBlack, final char x, final char y) {
        this.position = new Position(x, y);
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public Position getPosition() {
        return position;
    }

    public void moveTo(Position position) {
        this.position = position;
    }

    public boolean isEmpty() {
        return this instanceof Empty;
    }

    public boolean isSameColor(Piece other) {
        return this.isBlack == other.isBlack;
    }

    public abstract List<Direction> getDirections();

    public abstract int getStepRange();

    public abstract char getName();

    @Override
    public String toString() {
        return "Piece{" +
                "position=" + position +
                ", isBlack=" + isBlack +
                '}';
    }

    public void validateSourceAndTargetBeforeMove(Piece targetPiece){
        validatePositionInGrid(targetPiece);
        validateTargetPiece(targetPiece);
        validateSourcePiece();
    }

    public void validateTargetInMovablePositions(List<Position> movablePositions) {
        if (!movablePositions.contains(this.getPosition())) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private void validatePositionInGrid(Piece targetPiece){
        Position source = this.getPosition();
        Position target = targetPiece.getPosition();
        if (!source.isInGridRange() || !target.isInGridRange()) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private void validateTargetPiece(Piece targetPiece) {
        if (!targetPiece.isEmpty() && isSameColor(targetPiece)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private void validateSourcePiece() {
        if (isEmpty()) {   // TODO: + 자신의 말 색깔일때
            throw new IllegalArgumentException("자신의 말만 옮길 수 있습니다.");
        }
    }
}