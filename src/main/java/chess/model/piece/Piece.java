package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.File;
import chess.model.Rank;
import chess.model.Square;
import java.util.Objects;

public abstract class Piece {

    private final Color color;
    private Square square;

    public Piece(final Color color, final Square square) {
        this.color = color;
        this.square = square;
    }

    public Piece(final Color color, final File file, final Rank rank) {
        this(color, new Square(file, rank));
    }

    public abstract boolean movable(final Piece targetPiece);

    public abstract String getLetter();

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isAt(Square square) {
        return this.square.equals(square);
    }

    protected Color color() {
        return this.color;
    }

    protected Square square() {
        return this.square;
    }

    protected boolean isEnemy(Piece targetPiece) {
        return color.isEnemy(targetPiece.color);
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

    public abstract Point getPoint();

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

    public boolean isSameFile(Piece other) {
        return this.square.isSameFile(other.square);
    }
}
