package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;
import java.util.Objects;

public abstract class Piece {

    private final PieceType pieceType;
    private final Color color;

    protected Piece(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public String getName() {
        if (isBlack()) {
            return pieceType.getBlackName();
        }
        return pieceType.getWhiteName();
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public Color getColor() {
        return color;
    }

    public double getScore() {
        return pieceType.getScore();
    }

    public abstract boolean isMovableRoute(List<Position> routeFromStartToEnd);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return pieceType == piece.pieceType && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, color);
    }
}
