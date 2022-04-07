package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Locale;

public abstract class Piece {

    protected final PieceType type;
    protected final Color color;

    protected Piece(Color color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public abstract boolean canMove(Position fromPostion, Position toPosition);

    protected abstract List<Direction> getMovableDirections();

    public boolean isEmpty() {
        return color.isEmpty();
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameColor(Piece otherPiece) {
        return color == otherPiece.color;
    }

    public boolean isSamePieceType(PieceType pieceType) {
        return this.type == pieceType;
    }

    public String getSymbol() {
        String symbol = type.getSymbol();
        if (color.isWhite()) {
            return symbol.toLowerCase(Locale.ROOT);
        }
        return symbol;
    }

    public Color getColor() {
        return color;
    }

    public String convertToString() {
        if (isEmpty()) {
            return "empty";
        }
        return String.format("%s_%s", color.name().toLowerCase(), type.name().toLowerCase());
    }

    public double getPoint() {
        return type.getPoint();
    }
}
