package chess.domain;

public abstract class Rule {
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
    }

    private Type type;

    public Rule(final Type type) {
        this.type = type;
    }

    public boolean isValidMove(final Position origin, final Position target) {
        return false;
    }

    public boolean isValidAttack(final Position origin, final Position target) {
        return isValidMove(origin, target);
    }

    public double getScore() {
        return this.type.score;
    }

    public String getName() {
        return this.type.name;
    }

    public boolean isSameType(Type other) {
        return this.type == other;
    }
}
