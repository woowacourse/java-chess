package chess.domain.position;

public enum Rank {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');
    private final char rank;

    Rank(char rank) {
        this.rank = rank;
    }

    public char getRank() {
        return rank;
    }

    public int calculateRank(Rank other) {
        return rank - other.getRank();
    }

    public int calculateAbsoluteValue(Rank other) {
        return Math.abs(rank - other.getRank());
    }
}

