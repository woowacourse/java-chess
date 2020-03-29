package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.piece.direction.Direction;

import java.util.ArrayList;
import java.util.List;

import static chess.util.NullValidator.validateNull;

public abstract class Piece {
    protected final PieceColor pieceColor;
    private final String name;
    protected Position position;

    public Piece(String name, PieceColor pieceColor, Position position) {
        validateNull(name, pieceColor, position);

        this.name = name;
        this.pieceColor = pieceColor;
        this.position = position;
    }

    public boolean isNoneColor() {
        return pieceColor.isNoneColor();
    }

    public boolean isSameColor(Piece piece) {
        return this.pieceColor == piece.pieceColor;
    }

    public String getName() {
        return pieceColor.getPieceName(name);
    }

    public abstract List<Position> getPathTo(Position target);

    protected void validateEqualPosition(Position targetPosition) {
        validateNull(targetPosition);

        if (this.position.equals(targetPosition)) {
            throw new IllegalArgumentException("현재 자리한 위치로는 이동할 수 없습니다.");
        }
    }

    public Direction getDirection(Position targetPosition) {
        validateNull(targetPosition);

        int xPointDirectionValue = this.position.getXPointDirectionValueTo(targetPosition);
        int yPointDirectionValue = this.position.getYPointDirectionValueTo(targetPosition);
        return Direction.of(xPointDirectionValue, yPointDirectionValue);
    }

    protected List<Position> createPath(Position targetPosition, Direction direction) {
        validateNull(targetPosition, direction);

        List<Position> path = new ArrayList<>();

        Position currentPosition = this.position;
        while (!currentPosition.equals(targetPosition)) {
            Position changePosition = getChangePosition(currentPosition, direction);
            currentPosition = changePosition;
            path.add(changePosition);
        }

        return path;
    }

    private Position getChangePosition(Position currentPosition, Direction direction) {
        validateNull(currentPosition, direction);

        int changedXPoint = currentPosition.getXPoint().getValue() + direction.getXPoint();
        int changedYPoint = currentPosition.getYPoint().getValue() + direction.getYPoint();
        return Positions.of(changedXPoint, changedYPoint);
    }

    protected abstract void validateDistance(Position targetPosition);

    public void changeTo(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
