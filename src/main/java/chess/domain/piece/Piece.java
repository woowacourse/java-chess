package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Piece {

    private static final Map<String, Piece> cache = new HashMap<>();

    static {
        cache.put("WHITEKING", new King(Color.WHITE));
        cache.put("BLACKKING", new King(Color.BLACK));
        cache.put("WHITEQUEEN", new Queen(Color.WHITE));
        cache.put("BLACKQUEEN", new Queen(Color.BLACK));
        cache.put("WHITEROOK", new Rook(Color.WHITE));
        cache.put("BLACKROOK", new Rook(Color.BLACK));
        cache.put("WHITEKNIGHT", new Knight(Color.WHITE));
        cache.put("BLACKKNIGHT", new Knight(Color.BLACK));
        cache.put("WHITEBISHOP", new Bishop(Color.WHITE));
        cache.put("BLACKBISHOP", new Bishop(Color.BLACK));
        cache.put("WHITEPAWN", new Pawn(Color.WHITE));
        cache.put("BLACKPAWN", new Pawn(Color.BLACK));
        cache.put("EMPTYEMPTY", EmptyPiece.getInstance());
    }

    protected final Symbol symbol;
    protected final Color color;

    protected Piece(final Color color, final Symbol symbol) {
        this.color = color;
        this.symbol = symbol;
    }

    public static Piece of(final Color color, final Symbol symbol) {
        return cache.get(color.name() + symbol.name());
    }

    public static Piece of(final String color, final String symbol) {
        return cache.get(color + symbol);
    }

    public abstract boolean isMovable(final Position from, final Position to);

    public abstract double getPoint();

    protected boolean checkDirection(final Position from, final Position to, final List<Direction> directions) {
        final Direction direction;

        try {
            direction = Direction.getDirection(from, to);
            return directions.contains(direction);
        } catch (IllegalArgumentException e) {
            return false;
        }
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

    public final boolean isSameColor(final Piece otherPiece) {
        return color == otherPiece.color;
    }

    public final boolean isSameSymbol(final Symbol symbol) {
        return this.symbol == symbol;
    }

    public final boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public final Symbol getSymbol() {
        return symbol;
    }

    public final Color getColor() {
        return color;
    }

    public final String getPiece() {
        return color.name() + symbol.name();
    }
}
