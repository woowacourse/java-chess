package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.direction.Direction;

import java.util.ArrayList;
import java.util.List;

import static chess.util.NullValidator.validateNull;

public abstract class Piece {
    private final String name;
    protected final PieceColor pieceColor;
    private final double score;
    protected Position position;

    public Piece(String name, double score, PieceColor pieceColor, Position position) {
        validateNull(name, score, pieceColor, position);

        this.name = name;
        this.score = score;
        this.pieceColor = pieceColor;
        this.position = position;
    }

    public boolean isNone() {
        return pieceColor.isNoneColor();
    }

    public boolean isBlack() {
        return pieceColor.isBlack();
    }

    public boolean isSameColor(Piece piece) {
        return this.pieceColor == piece.pieceColor;
    }

    public boolean isSameColor(PieceColor color) {
        return this.pieceColor == color;
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
            Position changePosition = currentPosition.changeTo(direction);
            currentPosition = changePosition;
            path.add(changePosition);
        }

        return path;
    }

    protected abstract void validateDistance(Position targetPosition);

    public void changeTo(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public double getScore() {
        return score;
    }
}
