package chess.domain.board;

public enum XPosition {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final char name;

    XPosition(char name) {
        this.name = name;
    }

    public char getValue() {
        return name;
    }
}
