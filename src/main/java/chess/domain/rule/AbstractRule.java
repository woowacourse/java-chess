package chess.domain.rule;

import chess.domain.Position;
import chess.domain.Rule;

public abstract class AbstractRule implements Rule {
    public enum Type {
        PAWN("Pawn", 1),
        QUEEN("Queen", 9),
        ROOK("Rook", 5),
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

    private Type type;

    public AbstractRule(final Type type) {
        this.type = type;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return false;
    }

    @Override
    public boolean isValidAttack(final Position origin, final Position target) {
        return isValidMove(origin, target);
    }

    @Override
    public double getScore() {
        return this.type.score;
    }

    @Override
    public String getName() {
        return this.type.name;
    }
}
