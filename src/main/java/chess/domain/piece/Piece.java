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

    public String getName() {
        String symbol = name.getSymbol();
        if (color == Color.WHITE) {
            return symbol.toLowerCase(Locale.ROOT);
        }
        return symbol;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public boolean isEmpty() {
        return color == Color.EMPTY;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameColor(Piece otherPiece) {
        return color == otherPiece.color;
    }

    public boolean isSamePieceName(PieceName pieceName) {
        return this.name == pieceName;
    }

    public Color getColor() {
        return color;
    }
}
