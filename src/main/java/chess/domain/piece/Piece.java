package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.movestrategy.MoveStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Piece {

    private static final Map<String, Piece> PIECE_CACHE = new HashMap<>();

    static {
        whitePieces();
        blackPieces();
        PIECE_CACHE.put(Symbol.EMPTY.getBlack(), Empty.create());
    }

    private final Color color;
    private final Symbol symbol;

    public Piece(Color color, Symbol symbol) {
        this.color = color;
        this.symbol = symbol;
    }

    public static Piece of(final String symbol) {
        Piece piece = PIECE_CACHE.get(symbol);

        if (Objects.isNull(piece)) {
            throw new IllegalArgumentException("해당 심볼에 매칭되는 체스 말이 없습니다.");
        }

        return piece;
    }

    private static void whitePieces() {
        PIECE_CACHE.put(Symbol.QUEEN.getWhite(), Queen.createWhite());
        PIECE_CACHE.put(Symbol.KING.getWhite(), King.createWhite());
        PIECE_CACHE.put(Symbol.BISHOP.getWhite(), Bishop.createWhite());
        PIECE_CACHE.put(Symbol.KNIGHT.getWhite(), Knight.createWhite());
        PIECE_CACHE.put(Symbol.ROOK.getWhite(), Rook.createWhite());
        PIECE_CACHE.put(Symbol.PAWN.getWhite(), Pawn.createWhite());
    }

    private static void blackPieces() {
        PIECE_CACHE.put(Symbol.QUEEN.getBlack(), Queen.createBlack());
        PIECE_CACHE.put(Symbol.KING.getBlack(), King.createBlack());
        PIECE_CACHE.put(Symbol.BISHOP.getBlack(), Bishop.createBlack());
        PIECE_CACHE.put(Symbol.KNIGHT.getBlack(), Knight.createBlack());
        PIECE_CACHE.put(Symbol.ROOK.getBlack(), Rook.createBlack());
        PIECE_CACHE.put(Symbol.PAWN.getBlack(), Pawn.createBlack());
    }

    public abstract List<List<Position>> vectors(Position position);

    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return this.color.equals(Color.WHITE);
    }

    public boolean isSameColor(Color color) {
        return this.color.equals(color);
    }

    public boolean isNotSameColorPiece(Piece piece) {
        return !this.color.equals(piece.color);
    }

    public boolean isOppositeColorPiece(Piece piece) {
        if (this.color.equals(Color.BLACK) && piece.color.equals(Color.WHITE)) {
            return true;
        }
        return this.color.equals(Color.WHITE) && piece.color.equals(Color.BLACK);
    }

    public String getSymbol() {
        if (color.equals(Color.BLACK)) {
            return symbol.getBlack();
        }
        return symbol.getWhite();
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isNotEmpty() {
        return true;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isNotPawn() {
        return true;
    }

    public abstract MoveStrategy moveStrategy();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color && symbol == piece.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, symbol);
    }

    public abstract double score();
}
