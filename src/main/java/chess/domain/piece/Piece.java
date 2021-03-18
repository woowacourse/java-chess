package chess.domain.piece;

import chess.controller.ChessGame;
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

    public void moveTo(final Position position) {
        this.position = position;
    }

    public boolean isEmpty() {
        return this instanceof Empty;
    }

    public boolean isSameColor(final Piece other) {
        return this.isBlack == other.isBlack;
    }

    public void validateSourceAndTargetBeforeMove(final Piece targetPiece) {
        validatePositionInGrid(targetPiece);
        validateTargetPiece(targetPiece);
        validateSourcePiece();
    }

    public void validateTargetInMovablePositions(final List<Position> movablePositions) {
        if (!movablePositions.contains(this.getPosition())) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private void validatePositionInGrid(final Piece targetPiece) {
        Position source = this.getPosition();
        Position target = targetPiece.getPosition();
        if (!source.isInGridRange() || !target.isInGridRange()) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private void validateTargetPiece(final Piece targetPiece) {
        if (!targetPiece.isEmpty() && isSameColor(targetPiece)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private void validateSourcePiece() {
        if (isEmpty() || ChessGame.isBlackTurn() != isBlack()) {   // TODO: + 자신의 말 색깔일때
            throw new IllegalArgumentException("자신의 말만 옮길 수 있습니다.");
        }
    }

    public abstract List<Direction> getDirections();

    public abstract int getStepRange();

    public abstract char getName();

    public abstract double getScore();
}