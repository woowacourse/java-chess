package chess.domain.piece;

import chess.domain.PieceSymbol;
import chess.domain.Position;
import chess.domain.Rule;
import chess.domain.rule.Empty;
import chess.domain.rule.King;
import chess.domain.rule.Pawn;

import java.util.Objects;

public abstract class Piece {
    private Type type;
    private Color color;
    private Position position;

    public Piece(final Type type, final Color color, final Position position) {
        this.type = type;
        this.color = color;
        this.position = position;
    }

    boolean isWhite(){
        return this.color == Color.WHITE;
    }


    boolean isBlack(){
        return this.color == Color.BLACK;
    }

    public Type getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    static Piece empty() {
        return EMPTY;
    }

    boolean isSameColor(final Color other) {
        return color == other;
    }

    boolean isSameColor(final Piece other) {
        return this.color == other.color;
    }

    public boolean isValidMove(final Position origin, final Position target) {
        return rule.isValidMove(origin, target);
    }

    boolean isValidAttack(final Position origin, final Position target) {
        return rule.isValidAttack(origin, target);
    }

    boolean isEmpty() {
        return Color.EMPTY == color;
    }

    boolean isPawn() {
        for (Rule rule : Pawn.values()) {
            if (this.rule == rule) {
                return true;
            }
        }
        return false;
    }

    boolean isKing() {
        return this.rule == King.getInstance();
    }

    Piece get() {
        if (this.rule == Pawn.FIRST_BOTTOM) {
            return Piece.of(this.color, Pawn.SECOND_BOTTOM);
        }
        if (this.rule == Pawn.FIRST_TOP) {
            return Piece.of(this.color, Pawn.SECOND_TOP);
        }
        return Piece.of(this.color, this.rule);
    }

    public double getScore() {
        return rule.getScore();
    }

    String getSymbol() {
        return PieceSymbol.getSymbol(this.color, this.rule);
    }

    public enum Color {
        WHITE("WHITE"),
        BLACK("BLACK"),
        EMPTY("EMPTY");

        private final String name;

        Color(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum Type{
        PAWN("Pawn", 1),
        QUEEN("Queen", 9),
        ROOK("Rook",5),
        KNIGHT("Knight", 2.5),
        KING("King", 0),
        EMPTY("Empty", 0),
        BISHOP("Bishop", 3);

        private final String name;
        private final double score;

        Type(final String name, final double score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public double getScore() {
            return score;
        }
    }
}
