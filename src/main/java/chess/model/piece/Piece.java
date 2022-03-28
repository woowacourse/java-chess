package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import chess.model.piece.strategy.MovableStrategy;
import java.util.Objects;

public abstract class Piece {

    private final Color color;
    private Square square;
    private MovableStrategy strategy;

    public Piece(final Color color, final Square square, final MovableStrategy strategy) {
        this.color = color;
        this.square = square;
        this.strategy = strategy;
    }

    public abstract Point getPoint();

    public boolean movable(final Piece targetPiece) {
        return this.strategy.movable(this, targetPiece);
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isAt(Square square) {
        return this.square.equals(square);
    }

    public boolean isAlly(Piece targetPiece) {
        return color.isAlly(targetPiece.color);
    }

    public boolean isEmpty() {
        return color.isEmpty();
    }

    public Direction findDirectionTo(Piece target) {
        return this.square.findDirection(target.square);
    }

    public void changeLocation(Square targetSquare) {
        this.square = targetSquare;
    }

    public boolean isSameColor(Color color) {
        return this.color.equals(color);
    }

    public boolean isPawn() {
        return this.getPoint().equals(Point.PAWN);
    }

    public boolean isKing() {
        return this.getPoint().equals(Point.KING);
    }

    public boolean isEnemy(Piece targetPiece) {
        return color.isEnemy(targetPiece.color);
    }

    public boolean isSameFile(Piece other) {
        return this.square.isSameFile(other.square);
    }

    public int getDistance(Piece target) {
        return this.square.getDistance(target.square);
    }

    protected Color color() {
        return this.color;
    }

    protected Square square() {
        return this.square;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color && Objects.equals(square, piece.square);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, square);
    }
}
