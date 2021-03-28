package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.movestrategy.MoveStrategy;
import chess.domain.piece.team.Color;
import chess.domain.piece.team.Symbol;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final Color color;
    private final Symbol symbol;

    public Piece(Color color, Symbol symbol) {
        this.color = color;
        this.symbol = symbol;

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
