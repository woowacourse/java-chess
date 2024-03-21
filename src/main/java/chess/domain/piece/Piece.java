package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.position.Position;
import java.util.Objects;
import java.util.Set;

public abstract class Piece {
    protected final Set<Direction> directions;
    private final Color color;
    private final PieceType pieceType;

    public Piece(Color color, PieceType pieceType, Set<Direction> directions) {
        this.color = color;
        this.pieceType = pieceType;
        this.directions = directions;
    }

    public abstract Set<Position> calculateMovablePositions(Position currentPosition, Board board);

    public boolean isWhite() {
        return color.isWhite();
    }

    public boolean isEmpty() {
        return getPieceType() == PieceType.NONE;
    }

    public boolean isSameColor(Piece piece) {
        return color.isSame(piece.color);
    }

    public boolean isSameColor(Color other) {
        return color.isSame(other);
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Piece piece)) {
            return false;
        }
        return color == piece.color && pieceType == piece.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, pieceType);
    }
}
