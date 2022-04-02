package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class Piece {

    protected final PieceType type;
    protected final Color color;

    protected Piece(Color color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public abstract Map<Direction, List<Position>> getMovablePositions(Position position);

    public boolean isEmpty() {
        return color.isEmpty();
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameColor(Piece otherPiece) {
        return color == otherPiece.color;
    }

    public boolean isSamePieceName(PieceType pieceType) {
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

    public double getPoint() {
        return type.getPoint();
    }
}
