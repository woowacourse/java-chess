package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class Piece {

    protected final PieceName name;
    protected final Color color;

    protected Piece(Color color, PieceName name) {
        this.color = color;
        this.name = name;
    }

    public abstract Map<Direction, List<Position>> getMovablePositions(Position position);

    public abstract double getPoint();

    public final String getName() {
        String symbol = name.getSymbol();
        if (color == Color.WHITE) {
            return symbol.toLowerCase(Locale.ROOT);
        }
        return symbol;
    }

    public final boolean isBlack() {
        return color == Color.BLACK;
    }

    public final boolean isWhite() {
        return color == Color.WHITE;
    }

    public final boolean isEmpty() {
        return color == Color.EMPTY;
    }

    public final boolean isSameColor(Piece otherPiece) {
        return color == otherPiece.color;
    }

    public final boolean isSamePieceName(PieceName pieceName) {
        return this.name == pieceName;
    }

    public final Color getColor() {
        return color;
    }

    public final boolean isSameColor(Color color) {
        return this.color == color;
    }
}
