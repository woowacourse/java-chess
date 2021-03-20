package chess.domain.piece;

import chess.domain.piece.team.Color;
import chess.domain.piece.team.Symbol;

import java.util.Objects;

public abstract class Piece {
    private final Color color;
    private final Symbol symbol;

    public Piece(Color color, Symbol symbol) {
        this.color = color;
        this.symbol = symbol;

    }

    public String getSymbol() {
        if (color.equals(Color.BLACK)) {
            return symbol.getBlack();
        }
        return symbol.getWhite();
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && symbol == piece.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, symbol);
    }
}
